package br.com.fiap.environment.alert.repository;

import br.com.fiap.environment.alert.domain.AlertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertStatusRepository extends JpaRepository<AlertStatus, Long> {
}
