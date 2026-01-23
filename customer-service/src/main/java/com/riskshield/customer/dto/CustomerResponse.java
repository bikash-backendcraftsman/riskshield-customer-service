package com.riskshield.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.riskshield.customer.domain.enums.CustomerStatus;
import com.riskshield.customer.domain.enums.Occupations;
import com.riskshield.customer.domain.enums.RiskCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse{

        String customerId;
        RiskCategory riskCategory;
        CustomerStatus status;
}
