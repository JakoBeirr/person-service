package be.testprojects.springboot.services;

import be.testprojects.springboot.entities.Person;
import be.testprojects.springboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public void create(Person person) {
        personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
