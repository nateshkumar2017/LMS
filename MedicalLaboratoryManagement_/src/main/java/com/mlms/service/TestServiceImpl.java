package com.mlms.service;

import com.mlms.entities.Test;
import com.mlms.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestServiceInt{

    private final TestRepo testRepository;

    @Autowired
    public TestServiceImpl(TestRepo testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestByTestId(String testCode) {
        return testRepository.findByTestCode(testCode);
    }

    public Test createTest(Test test) {
        if (test.getId() == 0) {
            test.setId( (test.getId()));
        }
        return testRepository.save(test);
    }

    public Test updateTestByTestId(String testCode, Test updatedTest) {
        Test existingTest = testRepository.findByTestCode(testCode);
        if (existingTest != null) {
            existingTest.setTestName(updatedTest.getTestName());
            existingTest.setTestCode(updatedTest.getTestCode());
            existingTest.setPrice(updatedTest.getPrice());
            existingTest.setNormalRange(updatedTest.getNormalRange());
            existingTest.setUnit(updatedTest.getUnit());

            return testRepository.save(existingTest);
        } else {
            throw new RuntimeException("Test Not Found");
        }
    }

    public void deleteTestByTestId(String testCode) {
        Test existingTest = testRepository.findByTestCode(testCode);
        if (existingTest != null) {
            testRepository.delete(existingTest);
        } else {
            throw new RuntimeException("Test Not Found");
        }
    }

}
