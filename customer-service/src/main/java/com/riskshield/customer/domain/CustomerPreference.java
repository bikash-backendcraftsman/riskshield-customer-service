package com.riskshield.customer.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CustomerPreference {

    @Id
    @GeneratedValue
    private UUID preferenceId;

    private String language;
    private boolean marketingConsent;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
