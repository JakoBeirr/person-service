package be.testprojects.springboot.repositories;

import be.testprojects.springboot.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
