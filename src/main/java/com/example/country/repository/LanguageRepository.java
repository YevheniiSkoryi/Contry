package com.example.country.repository;

import java.util.Optional;

public interface LanguageRepository {

    Optional<Integer> getIdByLocalization(String localization);
}
