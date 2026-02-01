package com.riskshield.customer.api.response.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerApiResponse {
    private String customerId;
    private String email;
    private String action; //  "action": "ADD_ADDRESS",

}
