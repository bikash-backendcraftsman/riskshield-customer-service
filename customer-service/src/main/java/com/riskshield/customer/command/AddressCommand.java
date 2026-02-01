package com.riskshield.customer.command;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AddressCommand {

    /**
     * 📌 Notice
     *
     * No annotations like @Entity
     * No validation annotations
     * Immutable (final fields)
     *
     * Business-meaningful fields only
     */
    private final String addressType;
    private final String line1;
    private final String city;
    private final String state;
    private final String pincode;
}
