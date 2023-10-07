package com.mlms.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String patientName;

    private char gender;

    private int age;

    private String referedBy;

    private String contactNo;

    @OneToMany(mappedBy = "patientId")
    @JsonIgnore
    private List<Test> tests;

    @OneToMany(mappedBy = "patientId")
    @JsonIgnore
    private List<TestResult> testResults;

    @Transient
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Invoice> invoices;








}
