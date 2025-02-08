package com.enerisan.safetyNet.service;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("PersonService")
public class PersonService {
    private final PersonRepository personRepository;
    private final FirestationRepository firestationRepository;


    public PersonService(PersonRepository personRepository, FirestationRepository firestationRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
    }

    public List<Person> findAllPersons() {
        Iterable<Person> persons = personRepository.findAllPersons();
        List<Person> result = new ArrayList<>();
        persons.forEach(result::add);
        return result;
    }


    public List<Firestation> findAllFirestation() {
        Iterable<Firestation> firestations = firestationRepository.findAllFirestations();
        List<Firestation> result = new ArrayList<>();
        firestations.forEach(result::add);
        return result;
    }

    public List<String> findAllEmailsByCity(String city) {
        return this.personRepository.findAllPersons().stream().filter(p -> p.getCity().equals(city)).map(p -> p.getEmail()).collect(Collectors.toList());
    }

    public List<Firestation> findAllFirestationByStation(String station) {

        return this.firestationRepository.findAllFirestations().stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
    }


    public List<String> findAllTelephonesByFirestation(String station) {
        List<Firestation> firestationsByStation= this.firestationRepository.findAllFirestations().stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
        List <String> phones = new ArrayList<String>();
        for (Firestation firestation : firestationsByStation) {
            String firestationAddress = firestation.getAddress();
            List<Person> persons = findAllPersons();

            for (Person person : persons) {
                if (person.getAddress().equals(firestationAddress)) {
                    phones.add(person.getPhone());
                }
            }
        }
      return phones.stream().distinct().collect(Collectors.toList());
    }

}
