package com.enerisan.safetyNet;

import com.enerisan.safetyNet.controler.MedicalrecordController;
import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.MedicalrecordRepository;
import com.enerisan.safetyNet.service.MedicalrecordService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalrecordControllerTest {

    @Autowired
    MedicalrecordController medicalrecordController;

    @Autowired
    MedicalrecordRepository medicalrecordRepository;

    @Autowired
    MedicalrecordService medicalrecordService;

    @Test
    void getAllMedicalRecords() {
        Assertions.assertThat(!medicalrecordController.getAllMedicalRecords().isEmpty());
    }

    @Test
    void addMedicalrecord() {
        String[] medications = {"paracetamol", "ibuprofeno"};
        String[] allergies = {"polen"};
        Medicalrecord medicalrecord = new Medicalrecord("Lois", "Lane", "04/10/1980", medications, allergies);
        medicalrecordController.addMedicalrecord(medicalrecord);
        Medicalrecord result = medicalrecordRepository.findMedicalrecordByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        assert (result.equals(medicalrecord));
    }

    @Test
    void updateMedicalrecord() {
        Medicalrecord oldMedicalrecord = medicalrecordRepository.findMedicalrecordByFirstNameAndLastName("John", "Boyd");
        String[] updatedAllergies = {"polen"};
        Medicalrecord medicalrecordUpdate = new Medicalrecord(oldMedicalrecord.getFirstName(), oldMedicalrecord.getLastName(), oldMedicalrecord.getBirthdate(), oldMedicalrecord.getMedications(), updatedAllergies);
        medicalrecordService.updateMedicalrecord(medicalrecordUpdate, oldMedicalrecord.getFirstName(), oldMedicalrecord.getLastName());
        Assertions.assertThat(medicalrecordUpdate.getAllergies().equals(medicalrecordRepository.findMedicalrecordByFirstNameAndLastName(medicalrecordUpdate.getFirstName(), medicalrecordUpdate.getLastName()).getAllergies()));
    }

    @Test
    void deleteMedicalrecordByFirstNameAndLastName() {
        Integer numberOfMedicalRecords = medicalrecordRepository.findAllMedicalrecords().size();
        String[] medications = {"paracetamol", "ibuprofeno"};
        String[] allergies = {"polen"};
        Medicalrecord medicalrecord = new Medicalrecord("Lois", "Lane", "04/10/1980", medications, allergies);
        medicalrecordController.addMedicalrecord(medicalrecord);
        assertEquals(numberOfMedicalRecords + 1, medicalrecordRepository.findAllMedicalrecords().size());
       medicalrecordController.deleteMedicalrecordByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        assertEquals(numberOfMedicalRecords, medicalrecordRepository.findAllMedicalrecords().size());

    }
}