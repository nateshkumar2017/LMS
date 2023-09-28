package com.mlms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;


    //private Test testId;

    private float totalAmount;

    private float discount;

    @PrePersist
    public void generateInvoiceId() {
        if (invoiceId == null) {
            invoiceId = "in_" + (id + 100);
        }
    }
}
