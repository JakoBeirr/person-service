package be.testprojects.springboot.services;

import be.testprojects.springboot.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    void create(Person person);

    void delete(Long id);
}
