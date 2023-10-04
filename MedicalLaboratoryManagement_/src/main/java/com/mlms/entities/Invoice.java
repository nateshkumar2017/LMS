package com.mlms.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

//    @Column(unique=true)
//    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;


    //private Test testId;

    private float totalAmount;

    private float discount;


}
