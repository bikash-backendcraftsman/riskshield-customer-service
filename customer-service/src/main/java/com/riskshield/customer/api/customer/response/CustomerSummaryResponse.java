package com.riskshield.customer.api.customer.response;

import com.riskshield.customer.api.customer.response.enums.CustomerSummaryStatus;

/**
 * 📌 Used by:
 * 	•	Policy Service
 * 	•	Claims Service
 * 	•	Pricing Engine
 */
public class CustomerSummaryResponse {
    private String customerId;
    private String riskCategory;
    private CustomerSummaryStatus summaryStatus;
}
