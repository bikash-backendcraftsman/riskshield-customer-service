package com.riskshield.customer.application.validation.impl;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.application.validation.strategy.CustomerValidationStrategy;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.application.validation.strategy.BaseCustomerValidator;
import com.riskshield.customer.domain.enums.CustomerType;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class NRICustomerValidator extends BaseCustomerValidator implements CustomerValidationStrategy {

    public static final String PASSPORT_NUMBER = "passportNumber";

    // Indian passport: 1 letter + 7 digits — e.g. A1234567
    private static final Pattern PASSPORT_PATTERN =
            Pattern.compile("^[A-Z][0-9]{7}$");

    @Override
    public CustomerType supportedCustomerType() {
        return CustomerType.NRI;
    }


    @Override
    public ValidationResult validate(CreateCustomerApiRequest customerApiRequest) {
        Map<String, String> errors = new LinkedHashMap<>();

        // Common validations
        validateName(customerApiRequest.getFirstName(), "firstName", errors);
        validateName(customerApiRequest.getLastName(), "lastName", errors);
        validateEmail(customerApiRequest.getEmail(), errors);
        validMobileNumber(customerApiRequest.getMobileNumber(), errors);

        // NRI-specific validations
        validateDateOfBirth(customerApiRequest.getDob(),errors);
        validatePassportNumber(customerApiRequest.getPassportNumber(), errors);
        validateAnnualIncome(customerApiRequest.getAnnualIncome(), errors);

        return errors.isEmpty()
                ? ValidationResult.success()
                : ValidationResult.failureRecord(errors);
    }

    private static void validatePassportNumber(String passportNumber, Map<String, String> error){
        Optional.ofNullable(passportNumber)
                .filter(sm -> !sm.isBlank())
                .map(String::trim)
                .ifPresentOrElse(passport ->
                {
                    if(passport.isEmpty() ){
                        error.put(PASSPORT_NUMBER,"Please provide passport number with valid length");
                    }else if(!PASSPORT_PATTERN.matcher(passport.trim().toUpperCase()).matches()){
                        error.put(PASSPORT_NUMBER,"Passport format is invalid. Expected format: A1234567 (1 letter + 7 digits)\")");
                    }
                },()-> error.put(PASSPORT_NUMBER,"Passport Number must not be empty")
                );
    }
}
