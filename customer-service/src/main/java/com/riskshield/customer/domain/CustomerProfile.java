package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.CustomerStatus;
import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupation;
import com.riskshield.customer.domain.valueObjects.ContactInformation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

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
@Table(name = "customer_profile",indexes = {
        @Index(name = "idx_customer_email",columnList = "email"),
        @Index(name = "idx_customer_status",columnList = "status"),
        @Index(name = "idx_customer_type",columnList = "customer_type")
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"lifeCycle", "customerAddressList"}) // Avoid lazy loading in toString
@EqualsAndHashCode(of = "customerId") // Use business key
public class CustomerProfile {

    /* ==================== Identity ==================== */

    @Id
    @Column(columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    /* ==================== Basic Information ==================== */
    @NotBlank(message = "Customer name is required")
    @Column(nullable = false, length = 200)
    private String name;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    @Column(nullable = false)
    private LocalDate dateOfBirth;


    /* ==================== Contact Information (Embedded Value Object) ==================== */
    @Embedded
    @NotNull(message = "Contact information is required")
    private ContactInformation contactInformation;

    /* ==================== Classification ==================== */

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Customer type is required")
    @Column(name = "customer_type", nullable = false, length = 50)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private Occupation occupation;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Customer status is required")
    @Column(nullable = false, length = 50)
    private CustomerStatus status;

    /* ==================== Preferences (Embedded Value Object) ==================== */

    // NOTE: Changed from @OneToOne to @Embedded
    // Preferences are always loaded with customer and have no independent lifecycle
    // This simplifies the model and improves performance (no join needed)
    @Embedded
    private CustomerPreference preferences;

    /* ----------------- Relationships ----------------- */

    @OneToMany(
            mappedBy = "customerProfile",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<CustomerAddress> customerAddressList = new ArrayList<>();

    /* ----------------- Audit Fields ----------------- */

    @Column(updatable = false,nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    // NOTE: Added who created/updated for better audit trail
    @Column(updatable = false, length = 100)
    private String createdBy;

    @Column(length = 100)
    private String updatedBy;

    /* ==================== Lifecycle Callbacks ==================== */

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

}
