package com.mlms.service.implementation;

import com.mlms.dtos.TestAttributeResultDTO;
import com.mlms.entities.Order;
import com.mlms.entities.Test;
import com.mlms.entities.TestAttribute;
import com.mlms.entities.TestResult;
import com.mlms.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TestResultServiceImpl {

    @Autowired
    private TestResultRepo testResultRepository;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private TestRepo testRepository;

    @Autowired
    private TestAttributeRepo testAttributeRepository;

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    TestResult testResult;




//    public TestResult createTestResults(Long orderId, Long testId, List<TestAttributeResultDTO> attributeResults) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
//
//        Test test = testRepository.findById(testId)
//                .orElseThrow(() -> new IllegalArgumentException("Test not found with ID: " + testId));
//
//        List<TestResult> testResults = new ArrayList<>();
//
//        for (TestAttributeResultDTO attributeResultDTO : attributeResults) {
//            Long attributeId = attributeResultDTO.getAttributeId();
//            Float result = attributeResultDTO.getResult();
//
//            TestAttribute attribute = testAttributeRepository.findById(attributeId)
//                    .orElseThrow(() -> new IllegalArgumentException("Test Attribute not found with ID: " + attributeId));
//
//            TestResult testResult = new TestResult();
//            testResult.setOrder(order);
//            testResult.setTest(test);
//            testResult.setTestAttribute(attribute);
//            testResult.setResult(result);
//
//            if (order.getPatient() != null) {
//                test.setPatientId(order.getPatient());
//            }
//
//            testResults.add(testResult);
//        }
//
//        return testResultRepository.save(testResult);
//    }

    public List<TestResult> createTestResults(Long orderId, Long testId, List<TestAttributeResultDTO> attributeResults) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test not found with ID: " + testId));

        List<TestResult> testResults = new ArrayList<>();

        for (TestAttributeResultDTO attributeResultDTO : attributeResults) {
            Long attributeId = attributeResultDTO.getAttributeId();
            Float result = attributeResultDTO.getResult();

            TestAttribute attribute = testAttributeRepository.findById(attributeId)
                    .orElseThrow(() -> new IllegalArgumentException("Test Attribute not found with ID: " + attributeId));

            //TestResult testResult = new TestResult();
            testResult.setOrder(order);
            testResult.setTest(test);
            testResult.setTestAttribute(attribute);
            testResult.setResult(result);

            if (order.getPatient() != null) {
                test.setPatientId(order.getPatient());
            }

            testResults.add(testResult);
        }

        return testResultRepository.saveAll(testResults);
    }

}
