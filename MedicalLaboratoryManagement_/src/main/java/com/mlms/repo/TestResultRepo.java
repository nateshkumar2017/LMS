package com.mlms.repo;

import com.mlms.entities.Order;
import com.mlms.entities.TestAttribute;
import com.mlms.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepo  extends JpaRepository<TestResult,String> {
    TestResult findByOrderAndTestAttribute(Order order, TestAttribute testAttribute);
}
