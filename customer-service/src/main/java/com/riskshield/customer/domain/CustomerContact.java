package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.AddressType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CustomerContact {

    @Id
    @GeneratedValue
    private UUID contactId;

    private String mobileNumber;
    private String alternateContactNumber;
    private String email;
    private String preferCommunicationMode;

    @OneToOne
    @JoinColumn(name = "customerId")
    private CustomerProfile customerProfile;
}
