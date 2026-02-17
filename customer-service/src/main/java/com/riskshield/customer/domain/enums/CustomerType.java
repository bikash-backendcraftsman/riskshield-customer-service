package com.riskshield.customer.domain.enums;

/**
 * CustomerType Enum
 *
 * Represents the type/classification of customer.
 * Affects KYC requirements, transaction limits, and available services.
 */

public enum CustomerType {
    /**
     * Individual retail customer.
     * Standard KYC requirements.
     */
    INDIVIDUAL,

    /**
     * Corporate or business customer.
     * Enhanced KYC requirements, business verification needed.
     */
    CORPORATE,

    /**
     * Small/Medium Enterprise customer.
     * Moderate KYC requirements.
     */
    SME,

    /**
     * High Net Worth Individual.
     * Enhanced due diligence, premium services.
     */
    HNI,
    /**
     * Non-Resident Individual.
     * Additional compliance checks for cross-border regulations.
     */
    NRI,

    /**
     * Government or public sector entity.
     * Specialized KYC and compliance requirements.
     */
    GOVERNMENT,

    /**
     * Non-Profit Organization or NGO.
     * Special verification for charitable status.
     */
    NON_PROFIT

}
