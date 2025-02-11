package com.enerisan.safetyNet.repository;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FirestationRepository {
    private final DataHandler dataHandler;

    public FirestationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public List<Firestation> findAllFirestations() {
        return this.dataHandler.getData().getFirestations();
    }

    public Firestation findAllPersonsByFirestation(String address) {
       return this.dataHandler.getData().getFirestations().stream()
               .filter(f -> f.getAddress().equals(address))
               .findFirst()
               .orElseGet(() -> new Firestation());
    }


}

