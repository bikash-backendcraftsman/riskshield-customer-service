package com.riskshield.customer.application.validation.strategy;

import java.util.regex.Pattern;

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

    protected void validateName(){

    }
}
