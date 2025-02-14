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

    public Firestation findFirestationByAddress(String address) {
        return this.dataHandler.getData().getFirestations().stream()
                .filter(f -> f.getAddress().equals(address))
                .findFirst()
                .orElseGet(() -> new Firestation());
    }

    public List<Firestation> findAddressesByStation(String station) {
        return this.dataHandler.getData().getFirestations().stream().filter(f -> f.getStation().equals(station)).collect(Collectors.toList());
    }

    public Firestation add(Firestation firestation) {
        List<Firestation> firestations = dataHandler.getData().getFirestations();
        firestations.add(firestation);
        return firestation;
    }



    public void deleteFirestation(String station) {
        List <Firestation> firestation = findAddressesByStation(station);
        List<Firestation> allFirestations = dataHandler.getData().getFirestations();
        allFirestations.remove(firestation) ;
    }
}

