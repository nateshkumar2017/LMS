package com.mlms.controller;

import com.mlms.dtos.TestAttributeResultDTO;
import com.mlms.dtos.TestResultDTO;
import com.mlms.entities.Order;
import com.mlms.entities.ReportStatus;
import com.mlms.entities.TestResult;
import com.mlms.repo.OrderRepo;
import com.mlms.repo.ReportStatusRepo;
import com.mlms.service.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<TestResult> createTestResults(@RequestBody TestResultDTO testResultDTO) {
        Long orderId = testResultDTO.getOrderId();
        Long testId = testResultDTO.getTestId();
        List<TestAttributeResultDTO> attributeResults = testResultDTO.getAttributesResults();


        List<TestResult> testResults = testResultService.createTestResults(orderId, testId, attributeResults);

        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        ReportStatus availableStatus = reportStatusRepository.findByStatus(ReportStatus.StatusType.AVAILABLE);

        orderRepository.updateOrderStatus(orderId, availableStatus);


        return testResults;
    }

}
