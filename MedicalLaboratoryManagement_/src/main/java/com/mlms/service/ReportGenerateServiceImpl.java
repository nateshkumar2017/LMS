package com.mlms.service;

import com.mlms.dtos.ReportDTO;
import com.mlms.dtos.TestAttributeResultDTO;
import com.mlms.entities.*;
import com.mlms.repo.OrderRepo;
import com.mlms.repo.TestRepo;
import com.mlms.repo.TestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportGenerateServiceImpl {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private TestResultRepo testResultRepository;

    @Autowired
    private TestRepo testRepository;

    public ReportDTO generateReport(Long orderId) {
        // Fetch order information
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        // Fetch test information
        Test test = order.getTest();

        // Fetch patient information
        Patient patient = order.getPatient();

        // Create a ReportDTO and populate it with the required data
        ReportDTO report = new ReportDTO();
        report.setPatientName(patient.getPatientName());
        report.setPatientAge(patient.getAge());
        report.setPatientContactNumber(patient.getContactNo());
        //report.setPatientGender(patient.getGender());
        report.setPatientGender(String.valueOf(patient.getGender()));
        report.setOrderNo(orderId);
        report.setTestName(test.getTestName());

        // Fetch and populate test attributes and results
        List<TestAttributeResultDTO> testAttributes = new ArrayList<>();
        List<TestAttribute> attributes = test.getTestAttributes();
        for (TestAttribute attribute : attributes) {
            TestResult testResult = testResultRepository.findByOrderAndTestAttribute(order, attribute);
            if (testResult != null) {
                TestAttributeResultDTO attributeResult = new TestAttributeResultDTO();
                attributeResult.setAttributeName(attribute.getAttributeName());
                attributeResult.setNormalValue(attribute.getNormalValue());
                attributeResult.setResult(testResult.getResult());
                testAttributes.add(attributeResult);
            }
        }
        report.setTestAttributes(testAttributes);

        return report;
    }

}
