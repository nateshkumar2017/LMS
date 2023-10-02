package com.mlms.service;

import com.mlms.entities.Patient;
import com.mlms.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientServiceInt{



        private final PatientRepo patientRepository;

        @Autowired
        public PatientServiceImpl(PatientRepo patientRepository) {
            this.patientRepository = patientRepository;
        }

        @Override
        public Patient createPatient(Patient patient) {

            Patient createdPatient = patientRepository.save(patient);
            return createdPatient;
        }

    @Override
    public List<Patient> getAllPatients() {
            // Retrieve a list of all patients from the database
            return patientRepository.findAll();
        }

        @Override
        public Patient getPatientById(Long id) {
            // Retrieve a patient by their ID
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isPresent()) {
                return optionalPatient.get();
            } else {
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        }

        @Override
        public Patient updatePatient(Long id, Patient updatedPatient) {
            // Update an existing patient
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                // Update patient information with the provided data
                patient.setPatientName(updatedPatient.getPatientName());
                patient.setGender(updatedPatient.getGender());
                patient.setAge(updatedPatient.getAge());
                patient.setReferedBy(updatedPatient.getReferedBy());
                patient.setContactNo(updatedPatient.getContactNo());

                // Save the updated patient to the database
                return patientRepository.save(patient);
            } else {
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        }

        @Override
        public void deletePatient(Long id) {
            // Delete a patient by their ID
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isPresent()) {
                patientRepository.delete(optionalPatient.get());
            } else {
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        }


}
