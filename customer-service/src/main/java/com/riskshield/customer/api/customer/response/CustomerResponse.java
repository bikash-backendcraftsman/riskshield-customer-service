package com.riskshield.customer.api.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String customerId;
    private String name;
    private String email;
    private String occupation;
    private String status;

}
