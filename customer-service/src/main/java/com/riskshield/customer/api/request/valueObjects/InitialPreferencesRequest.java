package com.riskshield.customer.api.request.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitialPreferencesRequest {
    private String communicationChannel; // EMAIL, SMS, PHONE

    private String language; // en, hi, ta

    private Boolean notificationEnabled;

}
