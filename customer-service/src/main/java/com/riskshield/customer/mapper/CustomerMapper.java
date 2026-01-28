package com.riskshield.customer.mapper;

import com.riskshield.customer.domain.CustomerProfile;
import com.riskshield.customer.dto.CustomerResponse;

import java.util.Objects;

public final class CustomerMapper {

    public static CustomerResponse toResponse(CustomerProfile customer) {
        Objects.requireNonNull(customer, "customer must not be null");
        return null;
    }
}
