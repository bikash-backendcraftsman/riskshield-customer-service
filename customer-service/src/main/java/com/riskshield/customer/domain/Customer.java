package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.CustomerStatus;
import com.riskshield.customer.domain.enums.Occupations;
import com.riskshield.customer.domain.enums.RiskCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String customerId;
    private String name;
    private LocalDate LocalDateOfBirth;
    private String email;
    private String phoneNum;
    @Enumerated(EnumType.STRING)
    private Occupations occupation;
    @Enumerated(EnumType.STRING)
    private RiskCategory riskCategory;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
    private Instant createdAt;
    private Instant updatedAt;

}
