package com.riskshield.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.riskshield.customer.domain.enums.Occupation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNum;
    private Occupation occupation;
}
