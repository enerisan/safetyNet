package com.enerisan.safetyNet.repository;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirestationRepository {
    private final DataHandler dataHandler;

    public FirestationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public List<Firestation> findAllFirestations() {
        return dataHandler.getData().getFirestations();
    }

}

