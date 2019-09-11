package com.example.country.repository.impl;

import com.example.country.dto.CountryDTOOut;
import com.example.country.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    @org.intellij.lang.annotations.Language(value = "PostgreSQL")
    private final String FIND_COUNTRY_SQL = "SELECT id FROM country WHERE code = :code";

    @org.intellij.lang.annotations.Language(value = "PostgreSQL")
    private final String FIND_TRANSLATE_SQL = "SELECT c.code, cl.translate FROM country_language cl INNER JOIN country c ON cl.country_id = c.id  WHERE cl.country_id = :country_id AND cl.language_id = :language_id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<CountryDTOOut> mapper = (rs, rowNum) -> {
        final String code = rs.getString("code");
        final String translate = rs.getString("translate");
        return new CountryDTOOut(code, translate);
    };

    @Override
    public Optional<Integer> getIdByCode(String code) {
        try {
            final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("code", code.toUpperCase());

            final Integer countryId = namedParameterJdbcTemplate.queryForObject(
                FIND_COUNTRY_SQL,
                namedParameters,
                Integer.class
            );

            return Optional.ofNullable(countryId);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CountryDTOOut> getTranslateByCountryIdAndLanguageId(Integer countryId, Integer languageId) {
        try {
            final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("country_id", countryId)
                .addValue("language_id", languageId);

            final CountryDTOOut translate = namedParameterJdbcTemplate.queryForObject(
                FIND_TRANSLATE_SQL,
                namedParameters,
                mapper
            );

            return Optional.ofNullable(translate);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
