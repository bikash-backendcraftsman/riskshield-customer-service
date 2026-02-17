package com.riskshield.customer.command;

import com.riskshield.customer.domain.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class CreateAddressCommand {

    private final String addressLine1;
    private final String addressLine2;   // nullable — optional field
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
    private final AddressType addressType; // String → Enum conversion done in mapper
}
