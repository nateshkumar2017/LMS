package com.mlms.controller;

import com.mlms.dtos.TestResultDTO;
import com.mlms.entities.Order;
import com.mlms.entities.ReportStatus;
import com.mlms.entities.TestResult;
import com.mlms.repo.OrderRepo;
import com.mlms.repo.ReportStatusRepo;
import com.mlms.service.implementation.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
public class TestResultController {


    @Autowired
    private TestResultServiceImpl testResultService;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ReportStatusRepo reportStatusRepository;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_TECHNICIAN')")
    public List<TestResult> createTestResults(@RequestBody TestResultDTO testResultDTO) {
        //Long orderId = testResultDTO.getOrderId();
        //Long testId = testResultDTO.getTestId();
        //List<TestAttributeResultDTO> attributeResults = testResultDTO.getAttributesResults();

        List<TestResult> testResults = testResultService.createTestResults(testResultDTO.getOrderId(), testResultDTO.getTestId(), testResultDTO.getAttributesResults());

        Order orderToUpdate = orderRepository.findById(testResultDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + testResultDTO.getOrderId()));

        ReportStatus availableStatus = reportStatusRepository.findByStatus(ReportStatus.StatusType.AVAILABLE);

        orderRepository.updateOrderStatus(testResultDTO.getOrderId(), availableStatus);

        return testResults;
    }

}
