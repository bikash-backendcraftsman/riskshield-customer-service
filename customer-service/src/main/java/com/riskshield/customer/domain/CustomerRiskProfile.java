package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.RiskCategory;
import com.riskshield.customer.domain.enums.SourceType;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class CustomerRiskProfile {

    @Id
    @GeneratedValue
    private UUID riskId;

    private RiskCategory riskCategory;
    private SourceType sourceType;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
