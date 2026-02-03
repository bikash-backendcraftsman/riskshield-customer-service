package com.riskshield.customer.domain.valueObjects;

import com.riskshield.customer.domain.enums.CommunicationMode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@Getter
@Value
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ContactInformation {

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "Primary phone is required")
    @Column(name = "primary_phone", nullable = false, length = 20)
    private String primaryPhone; // Previously called mobileNumber in CustomerContact

    @Column(name = "alternate_phone", length = 20)
    private String alternatePhone; // Previously called alternateContactNumber

    @Column(name = "work_phone", length = 20)
    private String workPhone; // Additional field

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_communication_mode", length = 20)
    private CommunicationMode communicationMode = CommunicationMode.EMAIL;
}
