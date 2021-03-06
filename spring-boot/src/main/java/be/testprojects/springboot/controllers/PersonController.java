package be.testprojects.springboot.controllers;

import be.testprojects.springboot.entities.Person;
import be.testprojects.springboot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @PostMapping("/persons")
    public void create(@RequestBody Person person) {
        personService.create(person);
    }

    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
    }
}
