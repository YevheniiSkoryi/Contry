package com.example.country.exception;


public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException() {
    }

    public CountryNotFoundException(String s) {
        super(s);
    }
}
