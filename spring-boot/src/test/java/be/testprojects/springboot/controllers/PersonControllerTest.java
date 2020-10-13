package be.testprojects.springboot.controllers;

import be.testprojects.springboot.entities.Gender;
import be.testprojects.springboot.entities.Person;
import be.testprojects.springboot.repositories.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();
    }

    @Test
    public void shouldFindAll() throws Exception {
        // given
        Person person1 = new Person("First name 1", "Last name 1", Gender.MALE);
        Person person2 = new Person("First name 2", "Last name 2", Gender.FEMALE);
        personRepository.save(person1);
        personRepository.save(person2);

        // when
        mvc.perform(get("/persons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", equalTo(person1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", equalTo(person1.getLastName())))
                .andExpect(jsonPath("$[0].gender", equalTo(person1.getGender().name())))
                .andExpect(jsonPath("$[1].firstName", equalTo(person2.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", equalTo(person2.getLastName())))
                .andExpect(jsonPath("$[1].gender", equalTo(person2.getGender().name())));
    }

    @Test
    public void shouldCreate() throws Exception {
        // given
        Person person = new Person("First name", "Last name", Gender.OTHER);

        // when
        MvcResult result = mvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(person)))
                .andReturn();

        // then
        assertThat(result.getResponse().getStatus(), equalTo(HttpStatus.OK.value()));
        List<Person> persons = (List<Person>) personRepository.findAll();
        assertThat(persons, hasSize(1));
        assertThat(persons.get(0).getFirstName(), equalTo(person.getFirstName()));
        assertThat(persons.get(0).getLastName(), equalTo(person.getLastName()));
        assertThat(persons.get(0).getGender(), equalTo(person.getGender()));
    }

    @Test
    public void shouldDelete() throws Exception {
        // given
        Person person = new Person("First name", "Last name", Gender.OTHER);
        Person createdPerson = personRepository.save(person);

        // when
        MvcResult result = mvc.perform(delete("/persons/" + createdPerson.getId()))
                .andReturn();

        // then
        assertThat(result.getResponse().getStatus(), equalTo(HttpStatus.OK.value()));
        List<Person> persons = (List<Person>) personRepository.findAll();
        assertThat(persons, hasSize(0));
    }
}