package com.mlms.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ReportDTO {
    private String patientName;
    private int patientAge;
    private String patientContactNumber;
    private String patientGender;
    private Long orderNo;
    private String testName;
    private List<TestAttributeResultDTO> testAttributes;
}
