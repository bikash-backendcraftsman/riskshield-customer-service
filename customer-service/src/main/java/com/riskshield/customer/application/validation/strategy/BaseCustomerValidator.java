package com.riskshield.customer.application.validation.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaseCustomerValidator {

    // ── Compiled Patterns (static: compiled once, reused always) ──────────────

    protected static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Indian mobile: starts with 6-9, exactly 10 digits
    protected static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9]\\d{9}$");

    // PAN: 5 letters, 4 digits, 1 letter — e.g. ABCDE1234F
    protected static final Pattern PAN_PATTERN =
            Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

    // GST: 15-character alphanumeric
    protected static final Pattern GST_PATTERN =
            Pattern.compile("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$");

    protected static final int MINIMUM_AGE = 18;
    protected static final int MAXIMUM_AGE = 120;

    // ── Shared Validation Helpers ─────────────────────────────────────────────

    protected void validateName(String value,String fieldName,Map<String,String> error){
        if(value == null || value.isBlank()){
            error.put(fieldName, fieldName+"Must not be empty");
            return;
        }
        String trimmed = value.trim();
        if(trimmed.length() < 5){
            error.put(fieldName, fieldName + " "+ "Must be at least 5 character");
        } else if (trimmed.length() > 50) {
            error.put(fieldName,fieldName+" "+"Must not exceed 50 character");
        }
    }

    protected void validateEmail(String email,Map<String,String> error){
        if (email == null || email.isBlank()) {
            error.put("email", "Email must not be empty");
            return;
        }
        if(!EMAIL_PATTERN.matcher(email).matches()){
            error.put("email", "Email format is invalid. Expected: user@domain.com");
        }
    }

    protected void validMobileNumber(String phoneNumber,Map<String,String>error){
        if(phoneNumber == null || phoneNumber.isBlank()){
            error.put("phoneNumber","phone Number must not be empty");
        }
        String cleanedPhoneNumber = phoneNumber.trim().replaceAll("\\s+","");
        if(!PHONE_PATTERN.matcher(cleanedPhoneNumber).matches()){
            error.put("phoneNumber",
                    "Phone must be a valid 10-digit Indian mobile number starting with 6-9");
        }
    }

    protected void validateDateOfBirth(LocalDate dateOfBirth, Map<String,String> error){
        if(dateOfBirth == null){
            error.put("dateOfBirth","Date of Birth Must not be null");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            error.put("dateOfBirth", "Date of birth cannot be a future date");
            return;
        }

        long age = ChronoUnit.YEARS.between(dateOfBirth,LocalDate.now());
        if(age < MINIMUM_AGE){
            error.put("dateOfBirth",
                    "Customer must be at least " + MINIMUM_AGE + " years old. Calculated age: " + age);
        }else if(age > MAXIMUM_AGE){
            error.put("dateOfBirth", "Date of Birth Appears invalid.");
        }
    }

    /**
     * Validates annual income: not null, must be positive.
     */
    protected void validateAnnualIncome(BigDecimal income, Map<String, String> errors) {
        if (income == null) {
            errors.put("annualIncome", "Annual income must not be null");
            return;
        }
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            errors.put("annualIncome", "Annual income must be a positive number");
        }
    }

    /**
     * Validates PAN number format.
     */
    protected void validatePAN(String pan, Map<String, String> errors) {
        if (pan == null || pan.isBlank()) {
            errors.put("panNumber", "PAN number must not be empty");
            return;
        }
        if (!PAN_PATTERN.matcher(pan.trim().toUpperCase()).matches()) {
            errors.put("panNumber",
                    "PAN format is invalid. Expected format: ABCDE1234F");
        }
    }

    /**
     * Validates GST number format.
     */
    protected void validateGST(String gst, Map<String, String> errors) {
        if (gst == null || gst.isBlank()) {
            errors.put("gstNumber", "GST number must not be empty");
            return;
        }
        if (!GST_PATTERN.matcher(gst.trim().toUpperCase()).matches()) {
            errors.put("gstNumber",
                    "GST format is invalid. Expected 15-character format: 22ABCDE1234F1Z5");
        }
    }
}
