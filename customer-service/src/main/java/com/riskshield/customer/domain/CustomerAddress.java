package com.riskshield.customer.domain;

import com.riskshield.customer.domain.enums.AddressType;
import com.riskshield.customer.domain.valueObjects.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

/**
 * CustomerAddress Entity
 *
 * Represents a customer's address information.
 * Customers can have multiple addresses (home, work, billing, shipping, etc.)
 *
 * DESIGN NOTES:
 * - Part of CustomerProfile aggregate (NOT an aggregate root)
 * - Uses Address value object for actual address data
 * - Supports multiple address types per customer
 * - Only one primary address allowed per customer
 *
 * IMPROVEMENTS FROM ORIGINAL:
 * - Added Address as embedded value object (better encapsulation)
 * - Added isPrimary flag for default address selection
 * - Added validation constraints
 * - Added audit fields
 * - Added domain behavior methods
 * - Added address verification tracking
 * - Separated address data (value object) from metadata (entity)
 */

@Entity
@Table(name = "customer_address",indexes = {
        @Index(name = "idx_address_customer", columnList = "customer_id"),
        @Index(name = "idx_address_type", columnList = "address_type"),
        @Index(name = "idx_address_primary", columnList = "is_primary")
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "customerProfile") // Avoid lazy loading
@EqualsAndHashCode(of = "addressId")
public class CustomerAddress {

    /* ==================== Identity ==================== */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID addressId;

    @Embedded
    @Valid
    @NotNull(message = "Address information is required")
    private Address address;

    /* ==================== Address Metadata ==================== */
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Address type is required")
    @Column(name = "address_type",nullable = false,length = 50)
    private AddressType addressType;

    // NOTE: Added primary flag - only one address can be primary
    // This is useful for default address selection (e.g., for billing/shipping)
    @Column(name = "is_primary",nullable = false)
    private boolean isPrimary = false;

    // NOTE: Added active flag - allows "soft delete" of addresses
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;


    /* ==================== Address Verification ==================== */
    // NOTE: Added verification tracking for compliance (especially for financial services)
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @Column(name = "verified_at")
    private Instant verifiedAt;

    @Column(name = "verification_method", length = 100)
    private String verificationMethod; // e.g., "utility_bill", "bank_statement", "automated_service"

    @Column(name = "verification_reference", length = 200)
    private String verificationReference; // Reference number from verification service


    /* ==================== Audit Fields ==================== */
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by", updatable = false, length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;


    /* ==================== Relationship ==================== */
    /**
     * @JoinColumn(name = "customerId", nullable = false)
     *
     * Creates column customerId in this table (child table)
     * Acts as foreign key to customer table
     * nullable = false → must have a customer (database level)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId",unique = false)
    /**
     * @NotNull
     *
     * Bean validation (application level)
     * Validates before saving to database
     */
    @NotNull(message = "Customer profile is required")
    private CustomerProfile customerProfile;


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
