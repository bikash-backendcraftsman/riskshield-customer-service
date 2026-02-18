package com.riskshield.customer.application.validation.impl;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.application.validation.factory.CustomerValidationStrategy;
import com.riskshield.customer.application.validation.result.ValidationResult;
import com.riskshield.customer.application.validation.strategy.BaseCustomerValidator;
import com.riskshield.customer.domain.enums.CustomerType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CorporateCustomerValidator extends BaseCustomerValidator implements CustomerValidationStrategy {

    private static final BigDecimal MINIMUM_TURNOVER = new BigDecimal("100000"); // 1 Lakh minimum

    @Override
    public CustomerType supportedCustomerType() {
        return CustomerType.CORPORATE;
    }


    @Override
    public ValidationResult validate(CreateCustomerApiRequest customerApiRequest) {
        return null;
    }

    private void validateCompanyName(String companyName, Map<String,String> error){
        Optional.ofNullable(companyName)
                .filter(cm -> !cm.isBlank())
                .map(String::trim)
                .ifPresentOrElse(trimmed ->
                {
                    if(trimmed.length() < 20){
                        error.put("companyName", "Company name must be at least 20 characters");
                    }else if(trimmed.length() > 50){
                        error.put("companyName", "Company name must not exceed 50 characters");
                    }
                }, ()-> error.put("companyName", "Company name must not be empty")
                );
    }


    private void validateCompanyTurnOver(BigDecimal annualIncome,Map<String,String>error){
        if(annualIncome == null){
            error.put("annualIncome","Annual turnover must not be null");
            return;
        }

        if(annualIncome.compareTo(BigDecimal.ZERO) <= 0){
            error.put("annualIncome", "Annual turnover must be a positive number");
        } else if(annualIncome.compareTo(MINIMUM_TURNOVER) < 0){
            error.put("annualIncome",
                    "Annual turnover must be at least ₹1,00,000 for corporate registration");
        }
    }
}
