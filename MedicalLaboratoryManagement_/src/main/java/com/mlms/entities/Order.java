package com.mlms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.repository.Temporal;

//import javax.persistence.Entity;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import java.util.Date;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderId;

    //private User userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreatedDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTakenDateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ReportStatus reportStatus;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;


    @PrePersist
    public void generateOrderId() {
        if (orderId == null) {
            orderId = "p_" + (id + 100);
        }
    }

//    public Order(User user) {
//        this.user = user;
//    }


}
