package com.mlms.repo;

import com.mlms.entities.TestAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestAttributeRepo extends JpaRepository<TestAttribute,Long> {
    //Optional<Object> findById(Long attributeId);
}
