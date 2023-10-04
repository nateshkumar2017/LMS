package com.mlms.controller;

import com.mlms.entities.Patient;
import com.mlms.service.PatientServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    PatientServiceInt patientService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Patient> getAllPatients(){
        List<Patient> patients = patientService.getAllPatients();
        return patients;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Patient getPatientById(@PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        return patient;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Patient createPatient(@RequestBody Patient patient){
        Patient createdPatient = patientService.createPatient(patient);
        return createdPatient;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient){
        Patient patient = patientService.updatePatient(id, updatedPatient);
        return patient;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient deleted";
    }

}
