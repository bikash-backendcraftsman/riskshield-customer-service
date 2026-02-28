package com.riskshield.customer.application.validation.factory;

import com.riskshield.customer.application.validation.strategy.CustomerValidationStrategy;
import com.riskshield.customer.domain.enums.CustomerType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerValidationStrategyFactory {

    private final Map<CustomerType, CustomerValidationStrategy> strategyMap;


    public CustomerValidationStrategyFactory(List<CustomerValidationStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(CustomerValidationStrategy::supportedCustomerType,
                        Function.identity()));
    }

    /**
     * Returns the correct strategy for the given customer type.
     *
     * @param customerType The type declared in the API request
     * @return The matching strategy — guaranteed non-null
     * @throws UnsupportedCustomerTypeException if no strategy is registered for this type
     */

    public CustomerValidationStrategy getStrategy(CustomerType customerType){
        if(customerType == null){
            throw new IllegalArgumentException("Customer type must not be null");
        }

        CustomerValidationStrategy customerValidationStrategy = strategyMap.get(customerType);
        if (customerValidationStrategy == null) {
            throw new UnsupportedOperationException(
                    "No validation strategy registered for customer type: " + customerType +
                            ". Registered types: " + strategyMap.keySet()
            );
        }
        return customerValidationStrategy;
    }

    /**
     * Utility: check if a customer type is supported without throwing.
     */
    public boolean isSupported(CustomerType customerType) {
        return customerType != null && strategyMap.containsKey(customerType);
    }
}
