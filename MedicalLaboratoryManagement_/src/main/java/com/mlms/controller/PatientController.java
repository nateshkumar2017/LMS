package com.mlms.controller;

import com.mlms.entities.Patient;
import com.mlms.service.PatientServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    PatientServiceInt patientService;

    @GetMapping
    public List<Patient> getAllPatients(){
        List<Patient> patients = patientService.getAllPatients();
        return patients;
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        return patient;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        Patient createdPatient = patientService.createPatient(patient);
        return createdPatient;
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient){
        Patient patient = patientService.updatePatient(id, updatedPatient);
        return patient;
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient deleted";
    }

}
