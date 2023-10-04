package com.mlms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreatedDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTakenDateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    @JsonBackReference
    private Patient patient;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ReportStatus reportStatus;


}
