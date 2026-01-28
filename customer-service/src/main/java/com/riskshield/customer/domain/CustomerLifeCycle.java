package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.KYCStatus;

import java.time.LocalDateTime;

public class CustomerLifeCycle {
    private String onBoardingStage;
    private KYCStatus kycStatusRecord;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
