package com.riskshield.customer.exception;

public class DuplicateContactExistException extends RuntimeException {

    final String  message;

    public DuplicateContactExistException(String message) {
        this.message = message;
    }


}
