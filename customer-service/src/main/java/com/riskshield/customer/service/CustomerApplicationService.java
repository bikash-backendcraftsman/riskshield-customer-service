package com.riskshield.customer.service;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.api.response.CustomerCreatedApiResponse;

public interface CustomerApplicationService {

    CustomerCreatedApiResponse createCustomer(CreateCustomerApiRequest request);
}
