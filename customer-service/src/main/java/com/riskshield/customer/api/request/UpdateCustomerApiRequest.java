package com.riskshield.customer.api.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// For updating only profile information (not address, not preferences)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateCustomerApiRequest {

    @Size(min = 2, max = 100)
    private String firstName;

    @Size(max = 100)
    private String middleName;

    @Size(min = 2, max = 100)
    private String lastName;

    @Size(max = 100)
    private String occupation;

    @DecimalMin(value = "0.0")
    private BigDecimal annualIncome;
}
