package com.enerisan.safetyNet.service.dto;

import java.util.List;

public class FloodDto {
    String address;
    List<FireDto> people;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FireDto> getPeople() {
        return people;
    }

    public void setPeople(List<FireDto> people) {
        this.people = people;
    }
}