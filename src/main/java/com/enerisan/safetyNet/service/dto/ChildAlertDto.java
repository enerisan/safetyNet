package com.enerisan.safetyNet.service.dto;

import com.enerisan.safetyNet.model.Person;

import java.util.List;

public class ChildAlertDto {

    private String firstName;
    private String lastName;
    private String age;
    private List<Person> otherMembers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Person> getOtherMembers() {
        return otherMembers;
    }

    public void setOtherMembers(List<Person> otherMembers) {
        this.otherMembers = otherMembers;
    }
}