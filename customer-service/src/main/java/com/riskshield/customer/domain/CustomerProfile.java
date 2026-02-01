package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.CustomerStatus;
import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupation;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Core Entity
 *
 * CustomerProfile (ROOT)
 * |
 * |-- CustomerContact
 * |-- CustomerAddress (1..*)
 * |-- CustomerLifecycle
 * |-- CustomerRiskProfile (optional)
 * |-- CustomerPreference (optional)
 */


@Entity
@Table(name = "customer_profile")
public class CustomerProfile {
    @Id
    @Column(columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    /* ----------------- Relationships ----------------- */

    @OneToOne(mappedBy = "customerProfile",
            cascade=CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private CustomerContact customerContact;


    @OneToOne(
            mappedBy = "customerProfile",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private CustomerLifeCycle lifeCycle;

    @OneToMany(
            mappedBy = "customerProfile",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<CustomerAddress> customerAddressList = new ArrayList<>();

    /* ----------------- Audit Fields ----------------- */

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

}
