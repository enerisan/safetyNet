package com.enerisan.safetyNet.service;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.repository.MedicalrecordRepository;
import com.enerisan.safetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("MedicalrecordService")
public class MedicalrecordService {
    private final PersonRepository personRepository;
    private final FirestationRepository firestationRepository;
    private final MedicalrecordRepository medicalrecordRepository;


    public MedicalrecordService(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalrecordRepository medicalrecordRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalrecordRepository = medicalrecordRepository;
    }


    public List<Medicalrecord> findAllMedicalrecords() {
        Iterable<Medicalrecord> medicalrecords = medicalrecordRepository.findAllMedicalrecords();
        List<Medicalrecord> result = new ArrayList<>();
        medicalrecords.forEach(result::add);
        return result;
    }


    public Medicalrecord createMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordRepository.add(medicalrecord);
    }

    public void deleteMedicalrecordByFirstNameAndLastName(String firstName, String lastName) {
        medicalrecordRepository.deleteMedicalrecord(firstName, lastName);
    }

    public Medicalrecord updateMedicalrecord(Medicalrecord medicalrecord, String firstName, String lastName) {
        List<Medicalrecord> medicalrecords = medicalrecordRepository.findAllMedicalrecords();
        Medicalrecord Oldmedicalrecord = medicalrecordRepository.findMedicalrecordByFirstNameAndLastName(firstName, lastName);
        medicalrecords.remove(Oldmedicalrecord);
        Medicalrecord updatedMedicalrecord = new Medicalrecord();
        updatedMedicalrecord.setFirstName(firstName);
        updatedMedicalrecord.setLastName(lastName);
        updatedMedicalrecord.setBirthdate(medicalrecord.getBirthdate());
        updatedMedicalrecord.setMedications(medicalrecord.getMedications());
        updatedMedicalrecord.setAllergies(medicalrecord.getAllergies());
        medicalrecords.add(updatedMedicalrecord);
        return updatedMedicalrecord;
    }
}
