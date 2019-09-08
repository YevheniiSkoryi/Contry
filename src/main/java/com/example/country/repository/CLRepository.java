package com.example.country.repository;

import com.example.country.entity.CountryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CLRepository extends JpaRepository<CountryLanguage, Integer> {

    Optional<CountryLanguage> findByCountryIdAndLanguageId(Integer countryId, Integer languageId);
}
