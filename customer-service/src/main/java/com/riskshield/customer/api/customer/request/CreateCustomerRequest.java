package com.riskshield.customer.api.customer.request;

import com.riskshield.customer.domain.enums.Occupations;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    private String name;
    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    private LocalDateTime dob;
    @NotNull(message = "Occupation must be selected")
    private Occupations occupations;
}
