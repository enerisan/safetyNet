package com.enerisan.safetyNet.service;

import com.enerisan.safetyNet.model.Firestation;
import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.repository.FirestationRepository;
import com.enerisan.safetyNet.repository.MedicalrecordRepository;
import com.enerisan.safetyNet.repository.PersonRepository;
import com.enerisan.safetyNet.service.dto.ChildAlertDto;
import com.enerisan.safetyNet.service.dto.PersonInfoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    private final MedicalrecordRepository medicalrecordRepository;


    public PersonService(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalrecordRepository medicalrecordRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalrecordRepository = medicalrecordRepository;
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
        List<Firestation> firestationsByStation = this.firestationRepository.findAllFirestations().stream().filter(s -> s.getStation().equals(station)).collect(Collectors.toList());
        List<String> phones = new ArrayList<String>();
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

    public List<PersonInfoDto> findPersonInfo(String firstName, String lastName) {
        List<PersonInfoDto> personInfoDto = new ArrayList<>();
        Person person = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
        Medicalrecord medicalrecord = medicalrecordRepository.findMedicalrecordByFirstNameAndLastName(firstName, lastName);
        PersonInfoDto dto = new PersonInfoDto();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setAge(String.valueOf(computeAge(medicalrecord.getBirthdate())));
        dto.setEmail(person.getEmail());
        dto.setAllergies(medicalrecord.getAllergies());
        dto.setMedications(medicalrecord.getMedications());
        personInfoDto.add(dto);
        return personInfoDto;
    }

    private int computeAge(String birthdateOfPerson) {
        LocalDate formatedBirthDate = LocalDate.parse(birthdateOfPerson, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate curDate = LocalDate.now();
        int age = Period.between(formatedBirthDate, curDate).getYears();
        return age;
    }

    private boolean isMinor(int age) {
      if (age < 18 ) {
       return true;
      }
        return false;
    }

    public List<ChildAlertDto> findChildrenByAddress(String address) {

        List<ChildAlertDto> childAlertDto = new ArrayList<>();
        List<Person> persons = personRepository.findPersonsByAddress(address);

        for (Person person : persons) {
            Medicalrecord medicalrecord = medicalrecordRepository.findMedicalrecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            int age = computeAge(medicalrecord.getBirthdate());
            ChildAlertDto dto = new ChildAlertDto();
            if (isMinor(age)) {
                dto.setFirstName(person.getFirstName());
                dto.setLastName(person.getLastName());
                dto.setAge(String.valueOf(age));
                dto.setOtherMembers(persons.stream().filter(p -> !p.getFirstName().equals(person.getFirstName())).collect(Collectors.toList()));
                childAlertDto.add(dto);
            }

        }
        return childAlertDto;
    }


    public List<FireDto> findAllPersonsByFirestation(String address) {
        return null;
    }
}