package com.mlms.controller;

import com.mlms.dtos.TestAttributeResultDTO;
import com.mlms.dtos.TestResultDTO;
import com.mlms.entities.TestResult;
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



//    @PostMapping("/create")
//    public TestResult createTestResult(
//            @RequestParam Long orderId,
//            @RequestParam Long testId,
//            @RequestBody List<TestAttributeResultDTO> attributeResults
//    ) {
//        return testResultService.createTestResult(orderId, testId, attributeResults);
//    }

    @PostMapping("/create")
    public List<TestResult> createTestResults(@RequestBody TestResultDTO testResultDTO) {
        Long orderId = testResultDTO.getOrderId();
        Long testId = testResultDTO.getTestId();
        List<TestAttributeResultDTO> attributeResults = testResultDTO.getAttributesResults();
        return testResultService.createTestResults(orderId, testId, attributeResults);
    }

}
