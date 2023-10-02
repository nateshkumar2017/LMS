package com.mlms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDTO {
    private Long orderId;
    private Long testId;
    private List<TestAttributeResultDTO> attributesResults;
   // private Long patientId;
    //private String testName;
}
