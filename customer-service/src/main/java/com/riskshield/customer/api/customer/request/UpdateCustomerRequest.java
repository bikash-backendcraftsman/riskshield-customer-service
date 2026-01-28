package com.riskshield.customer.api.customer.request;

import com.riskshield.customer.domain.enums.Occupations;

public class UpdateCustomerRequest {
    private String email;
    private long mobileNumber;
    private String address;
    private Occupations updateOccupation;
    private int annualIncome;

}
