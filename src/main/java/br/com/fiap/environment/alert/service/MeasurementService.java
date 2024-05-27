package br.com.fiap.environment.alert.service;

import br.com.fiap.environment.alert.config.MailConfig;
import br.com.fiap.environment.alert.domain.AlertStatus;
import br.com.fiap.environment.alert.domain.RecordMeasurement;
import br.com.fiap.environment.alert.domain.ResourceIndex;
import br.com.fiap.environment.alert.domain.User;
import br.com.fiap.environment.alert.dto.AddMeasureInputDto;
import br.com.fiap.environment.alert.dto.GetRecordMeasureOutPutDto;
import br.com.fiap.environment.alert.enums.EnumStatus;
import br.com.fiap.environment.alert.exception.ObjectNotFoundException;
import br.com.fiap.environment.alert.repository.*;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {

    MeasurementRepository repository;
    ResourceIndexRepository indexRepository;
    AlertStatusRepository alertStatusRepository;
    EmailService emailService;
    ResourceRepository resourceRepository;
    MailConfig mailConfig;
    PersonRepository personRepository;
    UserRepository userRepository;

    public void add(final AddMeasureInputDto dto) {
        RecordMeasurement record = new RecordMeasurement();
        BeanUtils.copyProperties(dto, record);
        record.setResource(resourceRepository.findById(dto.resource()).orElseThrow(()
                -> new ObjectNotFoundException("resource not found")));
        ResourceIndex index = indexRepository.findById(record.getResource().getId()).orElseThrow(()
                -> new ObjectNotFoundException("Index for this resource do not exists"));
        repository.save(record);
        getIndexMeasure(record, index);
    }

    public Page<GetRecordMeasureOutPutDto> getListByLocation(final String location, final Pageable page) {
        Page<GetRecordMeasureOutPutDto> measureList = repository.findAllByLocation(location, page)
                .map(RecordMeasurement::toGetMeasureOutPutDto);
        if (measureList.isEmpty()) {
            throw new ObjectNotFoundException("Not found records for this location or page");
        }
        return measureList;
    }

    private void getIndexMeasure(final RecordMeasurement measure, final ResourceIndex index) {
        final BigDecimal measureValue = measure.getMeasurement();
        if (measureValue.compareTo(index.getIndMin()) < 0) {
            AlertStatus alertStatus = getAlertStatus(measure, EnumStatus.CALAMITY);
            if (!mailConfig.isHostMailNotConfigured()) {
                sendMail(alertStatus);
            }
        } else if (measureValue.compareTo(index.getIndMax()) >= 0) {
            AlertStatus alertStatus = getAlertStatus(measure, EnumStatus.IMMINENT_DANGER);
            if (!mailConfig.isHostMailNotConfigured()) {
                sendMail(alertStatus);
            }
        }

    }

    private void sendMail(final AlertStatus alertStatus) {
        List<User> listRegistredToSendMail = userRepository.findAllBySendAlertNotification(true);
        if (listRegistredToSendMail.isEmpty()) {
            return;
        }
        try {
            listRegistredToSendMail.stream().forEach(user -> {
                emailService.sendSimpleMessage(user.getPerson().getEmail(), alertStatus.getStatus(), "Run!!");
                alertStatus.getNotificationUser().add(user);
                alertStatusRepository.save(alertStatus);
            });

        } catch (Exception e1) {
            throw new ValidationException("Please Configure a mail for the application");
        }

    }

    private AlertStatus getAlertStatus(final RecordMeasurement measure, final EnumStatus enumStatus) {
        AlertStatus alertStatus = AlertStatus.builder()
                .status(enumStatus.getDescription())
                .dateTimeAlert(LocalDateTime.now())
                .recordMeasurement(measure)
                .build();
        alertStatusRepository.save(alertStatus);
        return alertStatus;
    }

}