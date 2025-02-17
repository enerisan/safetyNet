package com.enerisan.safetyNet.controler;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.service.PersonService;
import com.enerisan.safetyNet.service.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllpersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/communityEmail")
    public List<String>emails(@RequestParam(name = "city") String city) {
        return this.personService.findAllEmailsByCity(city);
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDto> getPersonInfo(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return this.personService.findPersonInfo(firstName, lastName);
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDto> getChildAlert(@RequestParam(name = "address") String address) {
        return this.personService.findChildrenByAddress(address);
    }

    ;

    @GetMapping("/fire")
    public List<FireDto> getAllPersonsByFirestation(@RequestParam(name = "address") String address) {
        return this.personService.findAllPersonsByFirestation(address);
    }

    @GetMapping("/flood")
    public List<FloodDto> getAllHomesByAddressAndStation(@RequestParam(name = "stations") List<String> stations) {
        return this.personService.findAllHomesByAddressAndStation(stations);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/person")
    public Person updatePerson(@RequestBody Person person, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return personService.updatePerson(person, firstName, lastName);
    }

    @DeleteMapping ("/person")
    public void deletePersonByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        personService.deletePersonByFirstNameAndLastName(firstName, lastName);
    }
}
