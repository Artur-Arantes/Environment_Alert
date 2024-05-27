package br.com.fiap.environment.alert.controller;

import br.com.fiap.environment.alert.dto.AddMeasureInputDto;
import br.com.fiap.environment.alert.dto.GetRecordMeasureOutPutDto;
import br.com.fiap.environment.alert.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/data")
public class MeasurementDataController {

    MeasurementService service;

    public MeasurementDataController(MeasurementService service) {
        this.service = service;
    }

    @PostMapping("add")
    public ResponseEntity addMeasurement(@RequestBody @Valid @NonNull final AddMeasureInputDto dto) {
        service.add(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/location")
    public ResponseEntity getMeasure(@RequestParam final String location,
                                     @PageableDefault(size = 2, page = 0)Pageable pagination) {
        Page<GetRecordMeasureOutPutDto> measuresByLocation = service.getListByLocation(location, pagination);
        return ResponseEntity.ok(measuresByLocation);
    }


}
