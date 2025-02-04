package model;

import java.util.List;

public class Data {
    private List<Persons> persons;
    private List<Firestations> firestations;
    private List<Medicalrecords> medicalrecords;



    public Data(List<Persons> persons, List<Firestations> firestations, List<Medicalrecords> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }
}
