package com.riskshield.customer.service.impl;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.api.response.CustomerCreatedApiResponse;
import com.riskshield.customer.application.validation.factory.CustomerValidationStrategyFactory;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.application.validation.strategy.CustomerValidationStrategy;
import com.riskshield.customer.service.CustomerApplicationService;
import com.riskshield.customer.util.StringNormalizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Application service responsible for customer-related use cases.
 *
 * <p>This service acts as a coordination layer between external API
 * requests and internal domain operations.</p>
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Translate API requests into domain commands</li>
 *   <li>Orchestrate domain services</li>
 *   <li>Return API-friendly responses</li>
 * </ul>
 *
 * <p>This layer contains no persistence logic and no HTTP concerns.</p>
 */

@Service
@Slf4j
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

   private final CustomerValidationStrategyFactory customerValidationStrategyFactory;
   private final CustomerUniquenessService customerUniquenessService;


    public CustomerApplicationServiceImpl(CustomerValidationStrategyFactory customerValidationStrategyFactory, CustomerUniquenessService customerUniquenessService) {
        this.customerValidationStrategyFactory = customerValidationStrategyFactory;
        this.customerUniquenessService = customerUniquenessService;
    }

    /**
     * Creates a new customer based on the provided API request.
     *
     * <p>Guarantees:
     * <ul>
     *   <li>Valid input has been provided</li>
     *   <li>Duplicate customers are not created</li>
     * </ul>
     *
     * @param createCustomerApiRequest API-level request containing customer details
     * @return response containing customer identifier and action performed
     */

    @Override
    public CustomerCreatedApiResponse onBoardNewCustomer(CreateCustomerApiRequest createCustomerApiRequest) {

        // ── Phase 1: Normalize data ──────────────────────────────────────
        // Do this FIRST before any validation
        String normalizedEmail = StringNormalizer.normalizeEmail(createCustomerApiRequest.getEmail());
        String normalizedPhoneNumber = StringNormalizer.normalizeMobileNumber(createCustomerApiRequest.getMobileNumber());

        // Update request with normalized values
        createCustomerApiRequest.setEmail(normalizedEmail);
        createCustomerApiRequest.setMobileNumber(normalizedPhoneNumber);

        // ── Phase 2: Validate business rules ─────────────────────────────
        // THIS IS WHERE YOUR STRATEGY PATTERN GOES
        CustomerValidationStrategy strategy = customerValidationStrategyFactory.getStrategy(createCustomerApiRequest.getCustomerType());
        ValidationResult validationResult = strategy.validate(createCustomerApiRequest);

        if(validationResult.hasErrors()){

        }
        return new CustomerCreatedApiResponse();
    }




    /**
     * ## The Key Distinction
     * ```
     * INFO  = "Something meaningful happened in the business"
     *            → User registered, Payment processed, Policy created
     *
     * DEBUG = "Something happened internally while processing"
     *            → Checking email, Validating format, Querying DB
     *
     */


    /**
     * Uniqueness Check Pattern — Key Learnings
     * ========================================
     * 1. BUG: normalize() returns new String — must CAPTURE the return value, not just call it
     * 2. BUG: Email stored lowercase, mobile without spaces — normalize correctly per field type
     * 3. BUG: Two separate DB checks = race condition — use single query OR database constraints
     * 4. DESIGN: Don't validate what upstream already validated — trust your pipeline contracts
     * 5. SEPARATION: Normalization is data transformation, uniqueness is business rule — different classes
     * 6. PRIVACY: Never log raw email/mobile — mask PII (b****h@gmail.com, ******1234)
     * 7. NAMING: Use ensureUnique() not assertUnique() — "ensure" means check-and-throw
     * 8. ATOMICITY: Check both fields in one DB call to prevent race conditions
     * 9. REUSABILITY: Normalize logic belongs in utility, not buried in service method
     * 10. CONTRACT: Document preconditions (email must be normalized) and postconditions (throws if duplicate)
     * One sentence: Uniqueness checking requires normalized data, atomic DB operations, privacy-safe logging, and clear separation between data transformation and business validation.
     */


}
