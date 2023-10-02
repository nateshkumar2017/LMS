package com.mlms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patientId;

    @ManyToOne
    @JoinColumn(name = "test_result_id")
    @JsonIgnore
    private TestResult testResult;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<TestAttribute> testAttributes = new ArrayList<>();





}
