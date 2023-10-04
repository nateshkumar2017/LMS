package com.mlms.repo;

import com.mlms.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TestRepo extends JpaRepository<Test, String> {

    Test findByTestCode(String testCode);


    //Optional<Object> findById(Long testId);
    Optional<Test> findById(Long testId);

    //Optional<Test> findById(Long id);
}
