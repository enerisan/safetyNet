package com.enerisan.safetyNet.model;

import java.util.List;

public class Data {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<Medicalrecord> medicalrecords;


    public Data() {
    }
    public Data(List<Person> persons, List<Firestation> firestations, List<Medicalrecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
