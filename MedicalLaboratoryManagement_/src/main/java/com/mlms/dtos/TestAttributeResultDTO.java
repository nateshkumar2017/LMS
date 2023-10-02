package com.mlms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAttributeResultDTO {

//    private Long attributeId;
//    //private Float result;
////    private Long attributeId;
//    private Long orderId;
//    private Long testId;
//    private List<TestAttributeResultDTO> attributeResults;

    private Long attributeId;
    private Float result;
}
