package com.riskshield.customer.domain.enums;

public enum AddressType {

    /**
     * Primary residential address.
     */
    HOME,

    /**
     * Work or office address.
     */
    WORK,

    /**
     * Billing address for invoices.
     */
    BILLING,

    /**
     * Shipping/delivery address.
     */
    SHIPPING,

    /**
     * Permanent address (for regulatory purposes).
     */
    PERMANENT,

    /**
     * Temporary or correspondence address.
     */
    TEMPORARY,

    /**
     * Other type of address.
     */
    OTHER
}
