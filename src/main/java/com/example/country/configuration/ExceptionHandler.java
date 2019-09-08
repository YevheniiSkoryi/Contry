package com.example.country.configuration;

import com.example.country.exception.CountryNotFoundException;
import com.example.country.exception.LanguageNotFoundException;
import com.example.country.exception.TranslateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity catchCountryNotFoundException(CountryNotFoundException exp){
        return new ResponseEntity(exp.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity catchLanguageNotFoundException(LanguageNotFoundException exp){
        return new ResponseEntity(exp.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity catchTranslateNotFoundException(TranslateNotFoundException exp){
        return new ResponseEntity(exp.getMessage(), HttpStatus.NOT_FOUND);
    }
}
