package com.enerisan.safetyNet.service;

import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service("PersonService")
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> findAllPersons(){
        Iterable<Person> persons = personRepository.findAllPersons();
        List<Person> result = new ArrayList<>();
        persons.forEach(result::add);
        return result;
    }

    public List<String> findAllEmailsByCity(String city) {
       return this.personRepository.findAllPersons().stream().filter(p ->p.getCity().equals(city)).map(p -> p.getEmail()).collect(Collectors.toList());
    }
}
