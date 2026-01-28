package com.riskshield.customer.api.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCreatedResponse {
    private String customerId;
    private String status;
    private String message;
}
