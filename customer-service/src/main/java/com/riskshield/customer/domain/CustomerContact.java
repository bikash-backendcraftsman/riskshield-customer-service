package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.AddressType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer_contact")
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID contactId;

    private String mobileNumber;
    private String alternateContactNumber;
    private String email;
    private String preferCommunicationMode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customerId",
            nullable = false,
            unique = true
    )
    private CustomerProfile customerProfile;
}
