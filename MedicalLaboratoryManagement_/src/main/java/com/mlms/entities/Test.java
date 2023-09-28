package com.mlms.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String testId;

    private String testName;

    private String testCode;

    private float price;

    private float normalRange;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @OneToOne(mappedBy = "test")
    private TestResult testResult;

    @OneToMany(mappedBy = "test")
    private List<Order> orders = new ArrayList<>();



    @PrePersist
    public void generateTestId() {
        if (testId == null) {
            testId = "t_" + (id + 100);
        }
    }


}
