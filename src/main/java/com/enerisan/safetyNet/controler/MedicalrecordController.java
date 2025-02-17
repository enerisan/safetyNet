package com.enerisan.safetyNet.controler;

import com.enerisan.safetyNet.model.Medicalrecord;
import com.enerisan.safetyNet.model.Person;
import com.enerisan.safetyNet.service.MedicalrecordService;
import com.enerisan.safetyNet.service.PersonService;
import com.enerisan.safetyNet.service.dto.ChildAlertDto;
import com.enerisan.safetyNet.service.dto.FireDto;
import com.enerisan.safetyNet.service.dto.FloodDto;
import com.enerisan.safetyNet.service.dto.PersonInfoDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class MedicalrecordController {

    private final MedicalrecordService medicalrecordService;

    public MedicalrecordController(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }

    @GetMapping("/getAllMedicalrecords")
    public List<Medicalrecord> getAllMedicalRecords() {
        return medicalrecordService.findAllMedicalrecords();
    }


    @PostMapping("/medicalrecord")
    public Medicalrecord addMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
        return medicalrecordService.createMedicalrecord(medicalrecord);
    }

    @PutMapping("/medicalrecord")
    public Medicalrecord updateMedicalrecord(@RequestBody Medicalrecord medicalrecord, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return medicalrecordService.updateMedicalrecord(medicalrecord, firstName, lastName);
    }

    @DeleteMapping ("/medicalrecord")
    public void deleteMedicalrecordByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
        medicalrecordService.deleteMedicalrecordByFirstNameAndLastName(firstName, lastName);
    }
}


