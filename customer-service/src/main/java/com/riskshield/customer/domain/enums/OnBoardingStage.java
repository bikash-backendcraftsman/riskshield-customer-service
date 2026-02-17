package com.riskshield.customer.domain.enums;

/**
 * OnBoardingStage Enum
 *
 * Represents the stages in the customer onboarding journey.
 *
 * FLOW:
 * REGISTRATION → DOCUMENT_SUBMISSION → KYC_VERIFICATION → ADDRESS_VERIFICATION → COMPLETED
 */
public enum OnBoardingStage {

    /**
     * Customer has started registration (filled basic info).
     */
    REGISTRATION,

    /**
     * Customer is submitting identity documents.
     */
    DOCUMENT_SUBMISSION,

    /**
     * KYC verification in progress (identity verification).
     */
    KYC_VERIFICATION,

    /**
     * Address verification in progress (proof of address).
     */
    ADDRESS_VERIFICATION,

    /**
     * Onboarding successfully completed.
     */
    COMPLETED,

    /**
     * Onboarding suspended (fraud suspicion, incomplete docs, etc.).
     */
    SUSPENDED
}
