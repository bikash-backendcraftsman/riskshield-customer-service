package com.riskshield.customer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/api/v1/customers/")
public class CustomerCommandController {

}
