package com.riskshield.customer.mapper;

import com.riskshield.customer.domain.Customer;
import com.riskshield.customer.dto.CustomerRequest;
import com.riskshield.customer.dto.CustomerResponse;

import java.util.Objects;

public final class CustomerMapper {

    public static CustomerResponse toResponse(Customer customer) {
        Objects.requireNonNull(customer, "customer must not be null");
        return null;
    }
}
