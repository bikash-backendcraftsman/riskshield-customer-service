package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.KYCStatus;
import com.riskshield.customer.domain.enums.OnBoardingStage;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_lifecycle")
public class CustomerLifeCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID lifecycleId;

    @Enumerated(EnumType.STRING)
    private OnBoardingStage onBoardingStage;
    private KYCStatus kycStatusRecord;

    /* ----------------- Audit Fields ----------------- */

    private Instant lastUpdatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customerId",
            nullable = false,
            unique = true
    )
    private CustomerProfile customerProfile;
}
