package com.mlms.service;

import com.mlms.entities.Patient;

import java.util.List;

public interface PatientServiceInt {

    public Patient createPatient(Patient patient);

    public List<Patient> getAllPatients();

    public Patient getPatientById(Long id);

    public Patient updatePatient(Long id, Patient updatedPatient);

    public void deletePatient(Long id);
}
