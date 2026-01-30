package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.AddressType;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "customer_address")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    @Column(nullable = false, unique = true)
    private String city;
    @Column(nullable = false,unique = true)
    private String state;
    @Column(nullable = false,unique = true)
    private int pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId",unique = false)
    private CustomerProfile customerProfile;

    private boolean primaryAddress;
}
