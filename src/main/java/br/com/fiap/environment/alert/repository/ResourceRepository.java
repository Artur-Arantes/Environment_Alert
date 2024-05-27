package br.com.fiap.environment.alert.repository;

import br.com.fiap.environment.alert.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Optional<Resource> findByName(String name);
}
