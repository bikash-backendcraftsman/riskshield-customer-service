package com.riskshield.customer.mapper;

import com.riskshield.customer.api.request.CreateCustomerApiRequest;
import com.riskshield.customer.api.request.valueObjects.InitialAddressRequest;
import com.riskshield.customer.api.request.valueObjects.InitialPreferencesRequest;
import com.riskshield.customer.command.CreateAddressCommand;
import com.riskshield.customer.command.CreateCustomerCommand;
import com.riskshield.customer.command.CreatePreferanceCommand;
import com.riskshield.customer.domain.enums.AddressType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerCommandMapper {

    public CreateCustomerCommand toCommand(CreateCustomerApiRequest request, String correlationId, String initiatedBy) {
        return CreateCustomerCommand.builder()
                // execution context — NOT from request body
                .commandId(UUID.randomUUID())
                .correlationId(correlationId)
                .initiatedBy(initiatedBy)

                // personal details — direct mapping
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender()) // String → Enum HERE
                .nationality(request.getNationality())
                .dob(request.getDob())

                // contact details
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())

                // classification
                .customerType(request.getCustomerType())
                .occupation(request.getOccupation())
                .annualIncome(request.getAnnualIncome())

                // nested mapping
                .initialAddress(toAddressCommand(request.getInitialAddressRequest()))
                .preferences(toPreferencesCommand(request.getPreferences()))

                .build();
    }

    private CreateAddressCommand toAddressCommand(InitialAddressRequest request) {
        return CreateAddressCommand.builder()
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .country(request.getCountry())
                .addressType(AddressType.valueOf(String.valueOf(request.getAddressType()))) // String → Enum
                .build();
    }

    private CreatePreferanceCommand toPreferencesCommand(InitialPreferencesRequest request) {
        if (request == null) return null;  // preferences is optional
        return CreatePreferanceCommand.builder()
                .communicationChannel(request.getCommunicationChannel())
                .language(request.getLanguage())
                .notificationEnabled(request.getNotificationEnabled())
                .build();
    }
}
