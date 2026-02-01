package com.riskshield.customer.api.request;

import com.riskshield.customer.domain.enums.Occupation;

public class UpdateCustomerApiRequest {
    private String email;
    private long mobileNumber;
    private String address;
    private Occupation updateOccupation;
    private int annualIncome;

}
