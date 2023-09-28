package com.mlms.service;

import com.mlms.entities.Test;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TestServiceInt {

    public List<Test> getAllTests();
    public Test getTestByTestId(String testCode);
    public Test createTest(Test test);
    public Test updateTestByTestId(String testCode, Test updatedTest);
    public void deleteTestByTestId(String testCode);

}
