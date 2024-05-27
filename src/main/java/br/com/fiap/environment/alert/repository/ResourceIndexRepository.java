package br.com.fiap.environment.alert.repository;

import br.com.fiap.environment.alert.domain.ResourceIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResourceIndexRepository extends JpaRepository<ResourceIndex, Long> {


    Page<ResourceIndex> findAll(Pageable pageable);
}
