package com.riskshield.customer.exception;

public class InvalidInputException extends RuntimeException {
    final String message;

    public InvalidInputException(String message){
        this.message = message;
    }
}

