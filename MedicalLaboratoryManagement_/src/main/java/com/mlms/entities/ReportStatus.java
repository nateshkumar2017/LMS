package com.mlms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String statusId;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    public enum StatusType {
        AVAILABLE,
        PENDING
    }

    @PrePersist
    public void generateStatusId() {
        if (statusId == null) {
            statusId = "s_" + (id + 100);
        }
    }


}
