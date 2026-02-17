package com.riskshield.customer.api.request;

import com.riskshield.customer.api.request.valueObjects.InitialAddressRequest;
import com.riskshield.customer.api.request.valueObjects.InitialPreferencesRequest;
import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerApiRequest {


    @NotBlank(message = "Customer first name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String firstName;


    @NotBlank(message = "Customer last name is required")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    private String lastName;


    @NotBlank(message = "Gender is required")
    private String gender; // MALE, FEMALE, OTHER


    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email format")
    private String email;

    /**
     * “The mobile number must be exactly 10 digits and must start with 6, 7, 8, or 9.”
     *
     * That’s the standard format for Indian mobile numbers.
     */
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please provide a valid Indian mobile number")
    private String mobileNumber;



    @NotBlank(message = "Nationality is required")
    private String nationality;


    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dob;


    @NotBlank(message = "Customer Type must be selected")
    private CustomerType customerType;


    @NotNull(message = "Occupation must be selected")
    private Occupation occupation;

    @DecimalMin(value = "0.0", message = "Annual income must be positive")
    private BigDecimal annualIncome;

    @Valid
    @NotNull(message = "Initial address is required")
    private InitialAddressRequest initialAddressRequest;

    @Valid
    private InitialPreferencesRequest preferences;

}
