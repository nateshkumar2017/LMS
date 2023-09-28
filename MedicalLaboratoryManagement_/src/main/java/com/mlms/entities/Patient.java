package com.mlms.entities;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patientId;

    private String patientName;

    private char gender;

    private int age;

    private String referedBy;

    private String contactNo;

    @OneToMany(mappedBy = "patientId")
    private List<Test> tests;

    @OneToMany(mappedBy = "patientId")
    private List<TestResult> testResults;

    @OneToMany(mappedBy = "patient")
    private List<Order> orders;

    @OneToMany(mappedBy = "patient")
    private List<Invoice> invoices;




    @PrePersist
    public void generatePatientId() {
        if (patientId == null) {
            patientId = "p_" + (id + 100);
        }
    }



}
