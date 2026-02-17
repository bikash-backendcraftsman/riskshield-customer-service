package com.riskshield.customer.domain.enums;

/**
 * CustomerStatus Enum
 *
 * Represents the current status of a customer account.
 *
 * STATUS LIFECYCLE:
 * PENDING → ACTIVE → SUSPENDED/INACTIVE/CLOSED
 */
public enum CustomerStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    PENDING,
    SUSPENDED,
    CLOSED;
}
