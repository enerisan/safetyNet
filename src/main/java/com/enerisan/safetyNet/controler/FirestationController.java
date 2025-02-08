package com.enerisan.safetyNet.controler;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("firestation")

public class FirestationController {

    private final PersonService personService;

    public FirestationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getAllFirestations")
    public List<Firestation> getAllfirestation() {
        return personService.findAllFirestation();
    }

    @GetMapping("/getAllFirestationsByStation")
    public List<Firestation> getAllFirestationByStation(@RequestParam(name = "station") String station) {
        return personService.findAllFirestationByStation(station).stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
    }
    @GetMapping("/phoneAlert")
    public  List<String>getAllTelephonesByFirestation(@RequestParam(name= "station") String station){
        return personService.findAllTelephonesByFirestation(station);
    }




}


