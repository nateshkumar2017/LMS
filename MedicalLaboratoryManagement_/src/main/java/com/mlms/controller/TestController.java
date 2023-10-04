package com.mlms.controller;

import com.mlms.entities.Test;
import com.mlms.service.TestServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private TestServiceInt testServiceInt;

    @Autowired
    public TestController(TestServiceInt testServiceInt) {
        this.testServiceInt = testServiceInt;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Test> findAllTests(){
        return testServiceInt.getAllTests();
    }

    @GetMapping("/{testCode}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Test getTestById(@PathVariable String testCode){
        return testServiceInt.getTestByTestCode(testCode);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Test addTest(@RequestBody Test test){
        return testServiceInt.createTest(test);
    }

    @PutMapping("/{testCode}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Test updateTest(@PathVariable String testCode, @RequestBody Test updatedTest){
        Test updated = testServiceInt.updateTestByTestCode(testCode, updatedTest);
        return updated;
    }

    @DeleteMapping("/{testCode}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTest(@PathVariable String testCode){
        testServiceInt.deleteTestByTestCode(testCode);
        return "Test Removed";
    }




}
