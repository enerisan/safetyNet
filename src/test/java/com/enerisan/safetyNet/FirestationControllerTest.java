package com.enerisan.safetyNet;

import com.enerisan.safetyNet.controler.FirestationController;
import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.service.FirestationService;
import com.enerisan.safetyNet.service.dto.FireStationDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirestationControllerTest {

    @Autowired
    FirestationController firestationController;

    @Autowired
    FirestationRepository firestationRepository;

    @Autowired
    FirestationService firestationService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllfirestation() {
        Assertions.assertThat(!firestationController.getAllfirestation().isEmpty());
    }

    @Test
    void getAllFirestationByStation() {
        Firestation firestation = new Firestation("8 Victoria road", "5");
        firestationController.addFirestation(firestation);
        Assertions.assertThat(!firestationController.getAllFirestationByStation(firestation.getStation()).isEmpty());
    }

    @Test
    void getFirestationByAddress() {
        Firestation firestation = new Firestation("8 Victoria road", "5");
        firestationController.addFirestation(firestation);
        Assertions.assertThat(!firestationController.getAllFirestationByStation(firestation.getAddress()).isEmpty());

    }

    @Test
    void getAllTelephonesByFirestation() {
        Assertions.assertThat(firestationController.getAllTelephonesByFirestation("1")).isNotNull();
    }

    @Test
    void getaddressbyStation() {
        Assertions.assertThat(firestationController.getaddressbyStation("1")).isNotNull();
    }

    @Test
    void getPeopleByStation() {
//        Assertions.assertThat(firestationController.getPeopleByStation("1")).isNotNull();
        FireStationDto result = firestationController.getPeopleByStation("2");
       Assertions.assertThat(result.getPeople().get(0).getFirstName().contains("Johnanthan"));

    }

    @Test
    void addFirestation() {
        Firestation firestation = new Firestation("8 Victoria road", "5");
        firestationController.addFirestation(firestation);
        Firestation result = firestationRepository.findFirestationByAddress(firestation.getAddress());
        Assertions.assertThat(result.getAddress().equals(firestation.getAddress()) && result.getStation().equals(firestation.getStation()));
    }

    @Test
    void updateFirestation() {
        Firestation firestation = new Firestation("8 Victoria road", "5");
        firestationController.addFirestation(firestation);
        Firestation firestationUpdate = new Firestation("7 Victoria road", "6");
        firestationService.updateFirestation(firestationUpdate, firestation.getAddress());
        Firestation result = firestationRepository.findFirestationByAddress(firestationUpdate.getAddress());
        Assertions.assertThat(result.getAddress().equals(firestationUpdate.getAddress()) && result.getStation().equals(firestationUpdate.getStation()));
    }

    @Test
    void deleteFirestationByStationNumber() {
        Integer numberOfFirestations = firestationRepository.findAllFirestations().size();
        Firestation firestation = new Firestation("8 Victoria road", "8");
        firestationController.addFirestation(firestation);
        assertEquals(numberOfFirestations + 1, firestationRepository.findAllFirestations().size());
        firestationController.deleteFirestationByStationNumber(firestation.getStation());
        assertEquals(numberOfFirestations, firestationRepository.findAllFirestations().size());
    }
}