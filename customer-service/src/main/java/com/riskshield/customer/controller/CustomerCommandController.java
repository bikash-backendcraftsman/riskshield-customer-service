package com.riskshield.customer.controller;

import com.riskshield.customer.api.request.*;
import com.riskshield.customer.api.response.CustomerCreatedApiResponse;
import com.riskshield.customer.api.response.common.CustomerApiResponse;
import com.riskshield.customer.common.web.APIResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * CustomerController exposes REST APIs for managing customer-related operations.
 *
 * <p>This controller acts as the entry point for all customer lifecycle requests
 * such as creating, retrieving, updating, and deleting customer information.
 * It validates incoming requests, delegates business logic to the service layer,
 * and returns appropriate HTTP responses.</p>
 *
 * <p>All endpoints are designed following RESTful principles and are intended
 * to be consumed by internal and external clients via the Customer Microservice.</p>
 *
 * @author Bikash
 * @since 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerCommandController {

    // 1️⃣ Create Customer
    @PostMapping
    public ResponseEntity<APIResponse<CustomerCreatedApiResponse>> createCustomer(@Valid @RequestBody CreateCustomerApiRequest customerApiRequest){

        return null;
    }

    // 2️⃣ Update Customer Contact
    @PutMapping(value = "/{customerId}/contact")
    public ResponseEntity<APIResponse<CustomerApiResponse>> updateContact(@PathVariable UUID customerId,
                                                                          @Valid @RequestBody UpdateCustomerApiRequest updateCustomerApiRequest){
        return ResponseEntity.ok(APIResponse.<CustomerApiResponse>builder().build());
    }

    // 3️⃣ Add Address
    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<APIResponse<CustomerApiResponse>> addAddress(
            @PathVariable UUID customerId,
            @Valid @RequestBody AddressAddApiRequest addressAddApiRequest){
        return ResponseEntity.ok(APIResponse.<CustomerApiResponse>builder().build());
    }

    // 4️⃣ Update Address
    @PutMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<APIResponse<CustomerApiResponse>> updateAddress(
            @PathVariable UUID customerId,
            @PathVariable UUID addressId,
            @Valid @RequestBody UpdateAddressApiRequest request) {


        return null;
    }

    // 5️⃣ Update Preferences
    @PutMapping("/{customerId}/preferences")
    public ResponseEntity<APIResponse<CustomerApiResponse>> updatePreferences(
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdatePreferencesApiRequest request) {



        return null;
    }

    // 6️⃣ Update Risk Profile
    @PutMapping("/{customerId}/risk-profile")
    public ResponseEntity<APIResponse<CustomerApiResponse>> updateRiskProfile(
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdateRiskProfileApiRequest request) {



        return null;
    }

    // 7️⃣ Suspend Customer
    @PostMapping("/{customerId}/suspend")
    public ResponseEntity<APIResponse<CustomerApiResponse>> suspendCustomer(
            @PathVariable UUID customerId,
            @Valid @RequestBody SuspendCustomerApiRequest request) {


        return null;
    }
}
