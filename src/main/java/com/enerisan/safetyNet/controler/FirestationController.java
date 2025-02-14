package com.enerisan.safetyNet.controler;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.service.FirestationService;
import com.enerisan.safetyNet.service.PersonService;
import com.enerisan.safetyNet.service.dto.FireStationDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController


public class FirestationController {

    private final PersonService personService;
    private final FirestationRepository firestationRepository;
    private final FirestationService firestationService;

    public FirestationController(PersonService personService, FirestationRepository firestationrepository, FirestationService firestationService) {
        this.personService = personService;
        this.firestationRepository = firestationrepository;
        this.firestationService = firestationService;
    }

    @GetMapping("/getAllFirestations")
    public List<Firestation> getAllfirestation() {
        return firestationService.findAllFirestation();
    }

    @GetMapping("/getAllFirestationsByStation")
    public List<Firestation> getAllFirestationByStation(@RequestParam(name = "station") String station) {
        return firestationService.findAllFirestationByStation(station).stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
    }

    @GetMapping("/getFirestationByAddress")
    public Firestation firestation(@RequestParam(name = "address") String address) {
        return firestationRepository.findFirestationByAddress(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> getAllTelephonesByFirestation(@RequestParam(name = "station") String station) {
        return personService.findAllTelephonesByFirestation(station);
    }

    @GetMapping("/addresses")
    public List<Firestation> getaddressbyStation(@RequestParam(name = "stationNumber") String station) {
        return firestationRepository.findAddressesByStation(station);

    }

    @GetMapping("/firestation")
    public FireStationDto getPeopleByStation(@RequestParam(name = "stationNumber") String station) {
        return personService.findPeopleByStation(station);
    }

    @PostMapping("/firestation")
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        return firestationService.createFirestation(firestation);
    }

    @PutMapping("/firestation")
    public Firestation updateFirestation(@RequestBody Firestation firestation, @RequestParam(name = "address") String address) {
        return firestationService.updateFirestation(firestation, address);
    }

    @DeleteMapping("/firestation")
    public void deleteFirestationByStationNumber(@RequestParam(name = "station") String station) {
        firestationService.deleteFirestationByStationNumber(station);
    }

    ;

}

