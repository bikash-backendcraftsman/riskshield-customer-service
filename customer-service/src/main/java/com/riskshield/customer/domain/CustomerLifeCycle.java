package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.KYCStatus;
import com.riskshield.customer.domain.enums.OnBoardingStage;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CustomerLifeCycle {
    @Id
    @GeneratedValue
    private UUID lifecycleId;

    @Enumerated(EnumType.STRING)
    private OnBoardingStage onBoardingStage;
    private KYCStatus kycStatusRecord;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "customerId")
    private CustomerProfile customerProfile;
}
