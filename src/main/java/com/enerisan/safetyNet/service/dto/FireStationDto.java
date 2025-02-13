package com.enerisan.safetyNet.service.dto;

import java.util.List;

public class FireStationDto {
List<FireStationPersonDto> people;
Integer numberOfAdults;
Integer numberOfChildren;


    public List<FireStationPersonDto> getPeople() {
        return people;
    }

    public void setPeople(List<FireStationPersonDto> people) {
        this.people = people;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}