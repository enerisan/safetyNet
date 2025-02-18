package com.enerisan.safetyNet.service;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.repository.MedicalrecordRepository;
import com.enerisan.safetyNet.repository.PersonRepository;
import com.enerisan.safetyNet.service.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("FirestationService")
public class FirestationService {
    private final PersonRepository personRepository;
    private final FirestationRepository firestationRepository;
    private final MedicalrecordRepository medicalrecordRepository;


    public FirestationService(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalrecordRepository medicalrecordRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalrecordRepository = medicalrecordRepository;
    }


    public List<Firestation> findAllFirestation() {
        Iterable<Firestation> firestations = firestationRepository.findAllFirestations();
        List<Firestation> result = new ArrayList<>();
        firestations.forEach(result::add);
        return result;
    }


    public List<Firestation> findAllFirestationByStation(String station) {

        return this.firestationRepository.findAllFirestations().stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
    }


    public Firestation createFirestation(Firestation firestation) {
        return firestationRepository.add(firestation);
    }

    public Firestation updateFirestation(Firestation firestation, String address) {
        Firestation oldFirestation = firestationRepository.findFirestationByAddress(address);
        List<Firestation> firestations = firestationRepository.findAllFirestations();
        firestations.remove(oldFirestation);
        Firestation newFirestation = new Firestation();
        newFirestation.setAddress(firestation.getAddress());
        newFirestation.setStation(firestation.getStation());
        firestations.add(newFirestation);
        return firestationRepository.findFirestationByAddress(newFirestation.getAddress());
    }

    public void deleteFirestationByStationNumber(String station) {
        firestationRepository.deleteFirestation(station);
    }


}