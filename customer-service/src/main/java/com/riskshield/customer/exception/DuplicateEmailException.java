package com.riskshield.customer.exception;

public class DuplicateEmailException extends RuntimeException {
    final String message;


    public DuplicateEmailException(String message) {
        this.message = message;
    }
}
