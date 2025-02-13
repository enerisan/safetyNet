package com.enerisan.safetyNet.repository;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonRepository {
    private final DataHandler dataHandler;

    public PersonRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }


    public List<Person> findAllPersons() {
        return dataHandler.getData().getPersons();
    }


    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return dataHandler.getData().getPersons().stream()
                .filter(p -> p.getFirstName().equals(firstName))
                .filter(p -> p.getLastName().equals(lastName))
                .findFirst()
                .orElseGet(() -> new Person());
    }

    public List<Person> findPersonsByAddress(String address) {
        //It returns the list of persons with the given address
        return dataHandler.getData().getPersons().stream().filter(p->p.getAddress().equals(address)).collect(Collectors.toList());
    }


}

