package com.mlms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
//Long


    private String testName;

    private String testCode;

    private float price;

    private float normalRange;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patientId;

    @OneToOne(mappedBy = "test")
    @JsonIgnore
    private TestResult testResult;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();





}
