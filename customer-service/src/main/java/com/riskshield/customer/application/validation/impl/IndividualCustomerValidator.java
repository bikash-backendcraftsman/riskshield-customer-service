package com.riskshield.customer.application.validation.impl;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.application.validation.strategy.CustomerValidationStrategy;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.application.validation.strategy.BaseCustomerValidator;
import com.riskshield.customer.domain.enums.CustomerType;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class IndividualCustomerValidator extends BaseCustomerValidator implements CustomerValidationStrategy {
    /**
     * Declares which customer type this strategy handles.
     * <p>
     * Called once at application startup by {@link CustomerValidationStrategy}
     * to build an internal Map of CustomerType → Strategy.
     * Not called on every request — only during Spring context initialization.
     * <p>
     * Contract:
     * - Never return null
     * - Each strategy must return a UNIQUE CustomerType
     * - Two strategies returning the same type will cause startup failure
     *
     * @return The {@link CustomerType} this strategy is responsible for validating.
     * Never null.
     */
    @Override
    public CustomerType supportedCustomerType() {
        return CustomerType.INDIVIDUAL;
    }

    /**
     * Validates all business rules applicable to this customer type.
     * <p>
     * Performs only business rule validation — format/null checks are handled
     * upstream by @Valid annotations on the controller.
     * <p>
     * This method is guaranteed to:
     * - Never throw an exception (validation failures go into ValidationResult)
     * - Never return null
     * - Collect ALL field errors in one pass (not stop at first failure)
     *
     * @param customerApiRequest The raw API request. Must not be null. Format already
     *                           verified by @Valid before this method is called.
     *
     */
    @Override
    public ValidationResult validate(CreateCustomerApiRequest customerApiRequest) {
        Map<String, String> error = new LinkedHashMap<>();

        // Common validations (inherited from BaseCustomerValidator)
        validateName(customerApiRequest.getFirstName(),"firstName",error);
        validateName(customerApiRequest.getLastName(),"lastName",error);
        validateEmail(customerApiRequest.getEmail(), error);
        validMobileNumber(customerApiRequest.getMobileNumber(), error);


        //
        validateDateOfBirth(customerApiRequest.getDob(),error);
        validatePAN(customerApiRequest.getPanNumber(),error);

        return error.isEmpty()
                ? ValidationResult.success()
                : ValidationResult.failureRecord(error);
    }
}
