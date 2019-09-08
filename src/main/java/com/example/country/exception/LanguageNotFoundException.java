package com.example.country.exception;


public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException() {
    }

    public LanguageNotFoundException(String s) {
        super(s);
    }
}
