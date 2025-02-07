package com.enerisan.safetyNet.controler;

import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllpersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/getEmails")
    public List<String>Emails(@RequestParam(name ="city") String city){
        return this.personService.findAllEmailsByCity(city);
    }
}
