package com.mlms.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TestAttributeResultDTO {

    @JsonIgnore
    private Long attributeId;
    private Float result;
    private String attributeName;
    private String normalValue;
}
