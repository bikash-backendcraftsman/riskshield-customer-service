package com.riskshield.customer.application.validation.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // ── QUERY METHODS (for CHECKING) ─────────────────────────────────────

    public Map<String, String> getFieldError() {
        return fieldError;
    }

    public boolean isValid() {
        return isValid;
    }

    /**
     * Returns true if validation failed (has errors).
     * Opposite of isValid() — provided for readability.
     *
     * @return true if has errors, false if valid
     */
    public boolean hasErrors() {
        return !isValid;
    }

    public List<String> getErrorMessage(Map<String,String> fieldError){
       return fieldError.entrySet().stream().map(e->e.getValue()).collect(Collectors.toList());
    }
}
