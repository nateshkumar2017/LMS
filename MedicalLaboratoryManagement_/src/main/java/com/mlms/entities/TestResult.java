package com.mlms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String resultid;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;


    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    private float result;

    @PrePersist
    public void generateTestResultId() {
        if (resultid == null) {
            resultid = "tr_" + (id + 100);
        }
    }


}
