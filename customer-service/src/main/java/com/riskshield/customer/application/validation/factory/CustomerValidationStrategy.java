package com.riskshield.customer.application.validation.factory;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.domain.enums.CustomerType;

/**
 * Strategy Interface — The CONTRACT for all customer validation strategies.
 *
 * WHY an interface here (not abstract class)?
 *   Every customer type has COMPLETELY different rules — there is no shared
 *   implementation logic worth inheriting. Just a shared contract.
 *
 * WHY supportedType() method on the interface?
 *   The Factory uses this to build a Map<CustomerType, Strategy> automatically.
 *   When Spring injects all implementations, the factory maps each to its type.
 *   You never write an if/else to pick the right strategy — the map does it.
 *
 * FUTURE EXTENSIBILITY:
 *   Adding a new customer type = create one new class implementing this interface.
 *   Zero existing files need to change. That is the entire point.
 *
 * Defines the contract for customer validation strategies.
 *
 *   Each implementation handles validation rules for one specific
 *   customer type (Individual, Corporate, NRI etc.).
 *
 *   HOW to add a new customer type:
 *     1. Create a class implementing this interface
 *     2. Annotate with @Component
 *     3. Return the new CustomerType from supportedType()
 *     Spring and the factory handle the rest automatically.
 *
 *   @see CustomerValidationStrategy
 *   @see ValidationResult
 *
 */
public interface CustomerValidationStrategy {

    /**
     * Declares which customer type this strategy handles.
     *
     * Called once at application startup by {@link CustomerValidationStrategy}
     * to build an internal Map of CustomerType → Strategy.
     * Not called on every request — only during Spring context initialization.
     *
     * Contract:
     *   - Never return null
     *   - Each strategy must return a UNIQUE CustomerType
     *   - Two strategies returning the same type will cause startup failure
     *
     * @return The {@link CustomerType} this strategy is responsible for validating.
     *         Never null.
     */
    CustomerType supportedCustomerType();


    /**
     * Validates all business rules applicable to this customer type.
     *
     * Performs only business rule validation — format/null checks are handled
     * upstream by @Valid annotations on the controller.
     *
     * This method is guaranteed to:
     *   - Never throw an exception (validation failures go into ValidationResult)
     *   - Never return null
     *   - Collect ALL field errors in one pass (not stop at first failure)
     *
     * @param customerApiRequest The raw API request. Must not be null. Format already
     *                verified by @Valid before this method is called.
     *
     */
    ValidationResult validate(CreateCustomerApiRequest customerApiRequest);
}
