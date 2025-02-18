package com.enerisan.safetyNet;

import com.enerisan.safetyNet.controler.PersonController;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.PersonRepository;
import com.enerisan.safetyNet.service.dto.PersonInfoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonInfoDtoTest {
    @Autowired
    PersonController personController;

    @Autowired
    PersonRepository personRepository;


    @Test
    void getFirstName() {
        Person person = personRepository.findPersonByFirstNameAndLastName("John","Boyd");
        List<PersonInfoDto> result = personController.getPersonInfo(person.getFirstName(), person.getLastName());
        Assertions.assertThat(result.stream().filter(f -> f.getFirstName().toString().equals(person.getFirstName())));
    }
//
//    @Test
//    void getLastName() {
//    }
//
//    @Test
//    void getAddress() {
//    }
//
//    @Test
//    void getAge() {
//    }
//
//    @Test
//    void getEmail() {
//    }
//
//    @Test
//    void getMedications() {
//    }
//
//    @Test
//    void getAllergies() {
//    }
}