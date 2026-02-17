package com.riskshield.customer.service;


import com.riskshield.customer.api.response.CustomerCreatedApiResponse;
import com.riskshield.customer.command.CreateCustomerCommand;

public interface CustomerApplicationService {

    CustomerCreatedApiResponse createCustomer(CreateCustomerCommand customerCommandRequest);
}
