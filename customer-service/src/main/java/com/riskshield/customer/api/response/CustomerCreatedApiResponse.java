package com.riskshield.customer.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCreatedApiResponse {
    private String customerId;
    private String action; //"action": "CREATE_CUSTOMER",
}
