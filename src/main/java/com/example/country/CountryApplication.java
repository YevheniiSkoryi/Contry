package com.example.country;

import com.example.country.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class CountryApplication  {


    public static void main(String[] args) {
        SpringApplication.run(CountryApplication.class, args);
    }

}
