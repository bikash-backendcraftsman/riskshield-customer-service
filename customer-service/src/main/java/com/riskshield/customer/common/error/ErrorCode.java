package com.riskshield.customer.common.error;

public enum ErrorCode {

    // 🔴 Validation Errors (400)
    REQUIRED_FIELD_MISSING("CUST-400-001", "Required field is missing"),
    INVALID_EMAIL("CUST-400-002", "Invalid email format"),
    INVALID_MOBILE("CUST-400-003", "Invalid mobile number"),
    DOB_IN_FUTURE("CUST-400-004", "Date of birth cannot be in the future"),
    INVALID_CUSTOMER_TYPE("CUST-400-005", "Invalid customer type"),

    // 🔴 Business Errors (409)
    CUSTOMER_ALREADY_EXISTS("CUST-409-001", "Customer already exists"),
    CUSTOMER_EMAIL_ALREADY_REGISTERED("CUST-409-002","Customer Email is already registered"),


    // 🔴 System Errors (500)
    DB_FAILURE("CUST-500-001", "Unable to process request"),
    INTERNAL_ERROR("CUST-500-999", "Internal server error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
