package com.riskshield.customer.api.response;

import com.riskshield.customer.api.response.enums.CustomerSummaryStatus;

/**
 * 📌 Used by:
 * 	•	Policy Service
 * 	•	Claims Service
 * 	•	Pricing Engine
 */
public class CustomerSummaryApiResponse {
    private String customerId;
    private String riskCategory;
    private CustomerSummaryStatus summaryStatus;
}
