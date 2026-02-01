package com.riskshield.customer.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
@Builder
@Getter

/**
 * 📌 Notice
 *
 * No annotations like @Entity
 *
 * No validation annotations
 *
 * Immutable (final fields)
 *
 * Business-meaningful fields only
 */

public final class CreateCustomerCommand {
    private final String firstName;
    private final String lastName;
    // Contact
    private final String email;
    private final String mobile;

    public final List<AddressCommand> address;
    private final String createdBy;
    private final String correlationId;

    public CreateCustomerCommand(
            String firstName,
            String lastName,
            String email,
            String mobile,
            List<AddressCommand> address,
            String createdBy,
            String correlationId
    ) {
        this.firstName = Objects.requireNonNull(firstName, "first name must not be null");
        this.lastName = Objects.requireNonNull(lastName, "last name must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.mobile = Objects.requireNonNull(mobile, "mobile must not be null");
        this.address = address;
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.correlationId = correlationId; // optional
    }

}
