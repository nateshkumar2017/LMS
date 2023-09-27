package com.mlms.entities;

import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.Temporal;

import java.util.Date;

public class Order {

    private String orderId;

    private ReportStatus statusId;

    private Invoice invoiceId;

    private User userId;

    private Test testId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreatedDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTakenDateTime;




}
