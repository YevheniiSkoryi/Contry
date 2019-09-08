package com.example.country.repository;

import com.example.country.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Optional<Language> findByLocalization(String localization);
}
