package com.riskshield.customer.application.validation.impl;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.application.validation.strategy.CustomerValidationStrategy;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.application.validation.strategy.BaseCustomerValidator;
import com.riskshield.customer.domain.enums.CustomerType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CorporateCustomerValidator extends BaseCustomerValidator implements CustomerValidationStrategy {

    private static final BigDecimal MINIMUM_TURNOVER = new BigDecimal("100000"); // 1 Lakh minimum
    public static final String ANNUAL_INCOME = "annualIncome";
    public static final String COMPANY_NAME = "companyName";

    @Override
    public CustomerType supportedCustomerType() {
        return CustomerType.CORPORATE;
    }


    @Override
    public ValidationResult validate(CreateCustomerApiRequest customerApiRequest) {
        Map<String, String> error = new LinkedHashMap<>();

        // For corporate, firstName is used as Company Name
        validateCompanyName(customerApiRequest.getFirstName(), error);

        // Contact person details
        validateEmail(customerApiRequest.getEmail(), error);
        validMobileNumber(customerApiRequest.getMobileNumber(), error);

        // Corporate-specific validations
        validateGST(customerApiRequest.getGst(), error);
        validateCompanyTurnOver(customerApiRequest.getAnnualIncome(), error); // reusing annualIncome field as turnover

        // NOTE: No age validation — companies are not people
        // NOTE: No PAN validation — corporate identity is via GST

        return error.isEmpty()
                ? ValidationResult.success()
                : ValidationResult.failureRecord(error);
    }

    private void validateCompanyName(String companyName, Map<String,String> error){
        Optional.ofNullable(companyName)
                .filter(cm -> !cm.isBlank())
                .map(String::trim)
                .ifPresentOrElse(trimmed ->
                {
                    if(trimmed.length() < 20){
                        error.put(COMPANY_NAME, "Company name must be at least 20 characters");
                    }else if(trimmed.length() > 50){
                        error.put(COMPANY_NAME, "Company name must not exceed 50 characters");
                    }
                }, ()-> error.put(COMPANY_NAME, "Company name must not be empty")
                );
    }


    private void validateCompanyTurnOver(BigDecimal annualIncome,Map<String,String>error){
        if(annualIncome == null){
            error.put(ANNUAL_INCOME,"Annual turnover must not be null");
            return;
        }

        if(annualIncome.compareTo(BigDecimal.ZERO) <= 0){
            error.put(ANNUAL_INCOME, "Annual turnover must be a positive number");
        } else if(annualIncome.compareTo(MINIMUM_TURNOVER) < 0){
            error.put(ANNUAL_INCOME,
                    "Annual turnover must be at least ₹1,00,000 for corporate registration");
        }
    }
}
