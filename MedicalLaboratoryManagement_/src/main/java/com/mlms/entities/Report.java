package com.mlms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reportId;

    @OneToOne(mappedBy = "report")
    private TestResult testResultId;


    @PrePersist
    public void generateReportId() {
        if (reportId == null) {
            reportId = "re_" + (id + 100);
        }
    }


}
