package com.mlms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderResponseDTO {

    private long patientId;
    private String patientName;
    private String patientContactNo;
    private char patientGender;
    private int patientAge;
    private String patientReferedby;
    private Date orderTime;
    private List<String> testNames;
    private float totalAmount;
    private float discount;
    private float amountAfterDiscount;
    private long invoiceId;


}
