package com.example.country.configuration;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

public class PostgresConf {

    public static Liquibase liquibase;

    public static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static {
        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();
        postgreSQLContainer.start();

        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName(postgreSQLContainer.getDriverClassName())
            .url(postgreSQLContainer.getJdbcUrl())
            .username(postgreSQLContainer.getUsername())
            .password(postgreSQLContainer.getPassword())
            .build();
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    static {
        Database database;
        try {
            database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                    new JdbcConnection(
                        namedParameterJdbcTemplate
                            .getJdbcTemplate()
                            .getDataSource()
                            .getConnection()
                    )
                );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        liquibase = new liquibase.Liquibase(
            "db-migration/changelog.xml",
            new ClassLoaderResourceAccessor(),
            database
        );
    }
}