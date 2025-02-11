package com.enerisan.safetyNet.repository;

import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalrecordRepository {
    private final DataHandler dataHandler;

    public MedicalrecordRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }


    public List<Medicalrecord> findAllMedicalrecords() {
        return dataHandler.getData().getMedicalrecords();
    }


    public Medicalrecord findMedicalrecordByFirstNameAndLastName(String firstName, String lastName) {
        return dataHandler.getData().getMedicalrecords().stream()
                .filter(m -> m.getFirstName().equals(firstName))
                .filter(m -> m.getLastName().equals(lastName))
                .findFirst()
                .orElseGet(() -> new Medicalrecord());
    }
}

