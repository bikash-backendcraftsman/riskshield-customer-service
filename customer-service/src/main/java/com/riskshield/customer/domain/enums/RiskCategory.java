package com.riskshield.customer.domain.enums;

public enum RiskCategory {

    LOW("Low Risk"),
    MEDIUM("Medium Risk"),
    HIGH("High Risk");

    private final String displayName;

    RiskCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
