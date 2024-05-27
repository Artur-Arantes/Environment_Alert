package br.com.fiap.environment.alert.repository;

import br.com.fiap.environment.alert.domain.RecordMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<RecordMeasurement, Long> {

    Page<RecordMeasurement> findAllByLocation(String location, Pageable pageable);
}
