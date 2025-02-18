package com.enerisan.safetyNet;

import com.enerisan.safetyNet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    void computeAge() {
        String birthDateOfPerson = "12/18/2015";
        assertEquals(9, personService.computeAge(birthDateOfPerson));
    }

}