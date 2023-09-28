package com.mlms.service;

import com.mlms.entities.Test;
import com.mlms.entities.TestAttribute;
import com.mlms.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestServiceInt {

    private final TestRepo testRepository;

    @Autowired
    public TestServiceImpl(TestRepo testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestByTestCode(String testCode) {
        return testRepository.findByTestCode(testCode);
    }

    public Test createTest(Test test) {
        for (TestAttribute attribute : test.getTestAttributes()) {
            attribute.setTest(test);
        }

        return testRepository.save(test);
    }

    public Test updateTestByTestCode(String testCode, Test updatedTest) {
        Test existingTest = testRepository.findByTestCode(testCode);
        if (existingTest != null) {
            existingTest.setTestName(updatedTest.getTestName());
            existingTest.setTestCode(updatedTest.getTestCode());
            existingTest.setPrice(updatedTest.getPrice());

            existingTest.getTestAttributes().clear();
            for (TestAttribute updatedAttribute : updatedTest.getTestAttributes()) {
                updatedAttribute.setTest(existingTest);
                existingTest.getTestAttributes().add(updatedAttribute);
            }

            return testRepository.save(existingTest);
        } else {
            throw new RuntimeException("Test Not Found");
        }
    }

    public void deleteTestByTestCode(String testCode) {
        Test existingTest = testRepository.findByTestCode(testCode);
        if (existingTest != null) {
            testRepository.delete(existingTest);
        } else {
            throw new RuntimeException("Test Not Found");
        }
    }
}
