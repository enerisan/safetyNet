package com.enerisan.safetyNet;

import com.enerisan.safetyNet.controler.PersonController;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.PersonRepository;
import com.enerisan.safetyNet.service.PersonService;
import com.enerisan.safetyNet.service.dto.ChildAlertDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonControllerTest {

    private static List<ChildAlertDto> childs = new ArrayList<>();
    @Autowired
    PersonController personController;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;


    @BeforeEach
    public void setUp() {


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllpersons() {
    }

    @Test
    void emailsTest() {
        Assertions.assertThat(personController.emails("Culver")).isNotNull();
    }

    @Test
    void getPersonInfo() {
        Assertions.assertThat(personController.getPersonInfo("John", "Boyd")).isNotNull();
    }

    @Test
    void getChildAlert() {
        Assertions.assertThat(personController.getChildAlert("1509 Culver St")).isNotNull();
        assertEquals(2, personController.getChildAlert("1509 Culver St").size());
    }

    @Test
    void getAllPersonsByFirestation() {
        Assertions.assertThat(personController.getAllPersonsByFirestation("1509 Culver St")).isNotNull();
        assertEquals(5, personController.getAllPersonsByFirestation("1509 Culver St").size());
    }

    @Test
    void getAllHomesByAddressAndStation() {
        List<String> stations = Arrays.asList("1", "2");
        Assertions.assertThat(personController.getAllHomesByAddressAndStation(stations)).isNotNull();
        assertEquals(6, personController.getAllHomesByAddressAndStation(stations).size());
    }

    @Test
    void addPerson() {
        Person person = new Person("Lois", "Lane", "test", "NY", "13100", "0506789", "person@gmail.com");
        personController.addPerson(person);
        Person result = personRepository.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        assert (result.getFirstName().equals(person.getFirstName()) && result.getLastName().equals(person.getLastName()));
    }

    @Test
    void updatePerson() {
        Person person = new Person("Lois", "Lane", "test", "NY", "13100", "0506789", "person@gmail.com");
        personRepository.add(person);
        Person personUpdate = new Person("Lois", "Lane", "8 Victoria Road", "NY", "13100", "0506789", "person@gmail.com");
        personService.updatePerson(personUpdate, "Lois", "Lane");

    }

    @Test
    void deletePersonByFirstNameAndLastName() {
        Integer numberOfPersons = personRepository.findAllPersons().size();
        Person person = new Person("Lois", "Lane", "test", "NY", "13100", "0506789", "person@gmail.com");
        personRepository.add(person);
        Assertions.assertThat(personRepository.findPersonByFirstNameAndLastName(person.getFirstName(),person.getLastName())).isNotNull();
        assertEquals(numberOfPersons+1, personRepository.findAllPersons().size());
        personController.deletePersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        assertEquals(numberOfPersons, personRepository.findAllPersons().size());
    }
}

