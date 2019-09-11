package com.example.country.exception;

public class LocalizationNotValid extends RuntimeException {

    public LocalizationNotValid() {
    }

    public LocalizationNotValid(String message) {
        super(message);
    }
}
