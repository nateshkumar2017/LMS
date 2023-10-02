package com.mlms.repo;

import com.mlms.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepo  extends JpaRepository<TestResult,String> {
}
