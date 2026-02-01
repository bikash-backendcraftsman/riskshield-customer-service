package com.riskshield.customer.api.request;

import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerApiRequest {

    /**
     * 🔧 Improvement 2: Class-Level Meaning
     *
     * Rename slightly for clarity:
     *
     * CreateCustomerApiRequest
     *
     *
     * Why?
     *
     * In real systems you’ll also have:
     *
     * CreateCustomerCommand
     *
     * CreateCustomerInternalDTO
     *
     * This avoids mental overload after 6 months
     *
     */

    @NotBlank(message = "Customer first name is required")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    private String firstName;
    @NotBlank(message = "Customer last name is required")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    private String lastName;
    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dob;
    @NotBlank(message = "Customer Type must be selected")
    private CustomerType customerType;
    @NotNull(message = "Occupation must be selected")
    private Occupation occupation;

    /**
     * “The mobile number must be exactly 10 digits and must start with 6, 7, 8, or 9.”
     *
     * That’s the standard format for Indian mobile numbers.
     */
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Please provide a valid Indian mobile number"
    )
    private String mobileNumber;
}
