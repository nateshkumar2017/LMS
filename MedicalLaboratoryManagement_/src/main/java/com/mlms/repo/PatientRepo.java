package com.mlms.repo;


import com.mlms.entities.Patient;
import com.mlms.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, String> {
    Optional<Patient> findById(Long id);

    Patient findByContactNo (String contactNo);
}
