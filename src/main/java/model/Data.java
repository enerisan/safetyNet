package model;

import java.util.List;

public class Data {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<Medicalrecord> medicalrecords;



    public Data(List<Person> persons, List<Firestation> firestations, List<Medicalrecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }
}
