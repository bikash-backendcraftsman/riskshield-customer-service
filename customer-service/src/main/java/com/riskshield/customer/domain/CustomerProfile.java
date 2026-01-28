package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.CustomerStatus;
import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupations;
import com.riskshield.customer.domain.enums.RiskCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Core Entity
 */
@Entity
@Table(name = "customer_profile")
public class CustomerProfile {
    @Id
    @GeneratedValue
    private UUID customerId;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @Enumerated(EnumType.STRING)
    private Occupations occupation;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @OneToOne(mappedBy = "customer",cascade=CascadeType.ALL)
    private CustomerContact customerContact;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private CustomerLifeCycle lifeCycle;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddressList = new ArrayList<>();

    private Instant createdAt;
    private Instant updatedAt;

}
