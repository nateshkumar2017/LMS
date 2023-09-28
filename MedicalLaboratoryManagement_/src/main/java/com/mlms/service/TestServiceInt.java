package com.mlms.service;

import com.mlms.entities.Test;

import java.util.List;

public interface TestServiceInt {

    List<Test> findAll();

    Test findById(String testId);



}
