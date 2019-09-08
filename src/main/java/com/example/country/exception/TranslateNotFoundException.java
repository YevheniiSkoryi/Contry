package com.example.country.exception;

public class TranslateNotFoundException extends RuntimeException{

    public TranslateNotFoundException() {
    }

    public TranslateNotFoundException(String message) {
        super(message);
    }
}
