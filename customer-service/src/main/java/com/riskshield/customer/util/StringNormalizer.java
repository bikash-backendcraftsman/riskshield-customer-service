package com.riskshield.customer.util;

public class StringNormalizer {

    private StringNormalizer(){

    }

    public static String normalizeEmail(String email){
        return email == null ? "" :email.trim().toLowerCase();
    }

    public static String normalizeMobileNumber(String mobileNumber){
        return mobileNumber == null ? "" :mobileNumber.trim().replaceAll("\\s+", "");
    }
}
