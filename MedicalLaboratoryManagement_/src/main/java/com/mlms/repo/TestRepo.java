package com.mlms.repo;

import com.mlms.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepo extends JpaRepository<Test, String> {

    Test findByTestCode(String testCode);


    //Optional<Object> findById(Long testId);
    Optional<Test> findById(Long testId);

    //Optional<Test> findById(Long id);
}
