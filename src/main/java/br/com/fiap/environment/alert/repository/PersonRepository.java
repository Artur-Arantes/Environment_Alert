package br.com.fiap.environment.alert.repository;

import br.com.fiap.environment.alert.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {

}
