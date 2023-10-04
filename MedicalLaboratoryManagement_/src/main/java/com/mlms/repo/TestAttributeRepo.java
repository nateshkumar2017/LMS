package com.mlms.repo;

import com.mlms.entities.TestAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TestAttributeRepo extends JpaRepository<TestAttribute,Long> {
    //Optional<Object> findById(Long attributeId);
}
