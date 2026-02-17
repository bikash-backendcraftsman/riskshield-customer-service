package com.riskshield.customer.domain.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Address - Embedded Value Object
 *
 * Represents a physical address.
 * This is a value object - it has no identity, only values.
 *
 * VALUE OBJECT CHARACTERISTICS:
 * - Immutable (or treated as such)
 * - No identity (equality based on values, not ID)
 * - Replaceable (you replace the entire address, not modify it)
 * - Self-validating (contains validation logic)
 *
 * DESIGN RATIONALE:
 * - Addresses are complex but have no independent lifecycle
 * - Multiple entities might use addresses (CustomerAddress, OfficeAddress, etc.)
 * - Encapsulates address validation and formatting logic
 * - Can be reused across the domain
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {

    /* ==================== Address Components ==================== */

    @NotBlank(message = "Street address is required")
    @Column(name = "address_line1", nullable = false, length = 255)
    private String addressLine1; // Primary street address

    @Column(name = "address_line2", length = 255)
    private String addressLine2; // Apartment, suite, unit, etc.

    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotBlank(message = "State/Province is required")
    @Column(name = "state", nullable = false, length = 100)
    private String state; // State, Province, or Region

    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode; // ZIP code, postal code, or PIN code

    @NotBlank(message = "Country is required")
    @Column(name = "country", nullable = false, length = 100)
    private String country; // ISO 3166-1 alpha-2 country code (e.g., "US", "IN", "GB")

    /* ==================== Additional Fields ==================== */

    @Column(name = "landmark", length = 200)
    private String landmark; // Nearby landmark (useful in some countries)
}
