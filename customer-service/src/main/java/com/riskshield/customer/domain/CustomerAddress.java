package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.AddressType;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class CustomerAddress {

    @Id
    @GeneratedValue
    private UUID addressId;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String city;
    private String state;
    private int pinCode;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerProfile customerProfile;
}
