package com.riskshield.customer.application.validation.result;

import java.util.Collections;
import java.util.Map;

public class ValidationResult {
    private final boolean isValid;
    private final Map<String,String> fieldError;

    // Private constructor: force callers to use factory methods
    private ValidationResult(boolean isValid, Map<String, String> fieldError) {
        this.isValid = isValid;
        // Unmodifiable so nobody can tamper with the result after creation
        this.fieldError = Collections.unmodifiableMap(fieldError);
    }

    // ── Factory Methods ───────────────────────────────────────────────────────

    /**
     * Call this when ALL validations pass.
     */
    public static ValidationResult success() {
        return new ValidationResult(true, Collections.emptyMap());
    }

    public static ValidationResult failureRecord(Map<String, String> errors){
        if(errors == null || errors.isEmpty()){
            throw new IllegalArgumentException("Cannot create failure result with empty errors. Use success() instead.");
        }
        return new ValidationResult(false,errors);
    }
}
