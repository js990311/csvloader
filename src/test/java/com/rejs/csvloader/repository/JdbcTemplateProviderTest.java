package com.rejs.csvloader.repository;

import com.rejs.csvloader.TestcontainersConfiguration;
import com.rejs.csvloader.yaml.properties.config.DatabaseProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JdbcTemplateProviderTest {

    @Autowired
    private MariaDBContainer<?> mariaDbContainer;

    @Autowired
    private PostgreSQLContainer<?> postgresContainer;

    private String username = "myuser";
    private String secret = "secret";
    private JdbcTemplateProvider jdbcTemplateProvider = new JdbcTemplateProvider();

    @Test
    void createJdbcTemplatePostgresql() {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        databaseProperties.setDriver("org.postgresql.Driver");
        databaseProperties.setHost(postgresContainer.getJdbcUrl());
        databaseProperties.setUsername(username);
        databaseProperties.setPassword(secret);
        JdbcTemplate jdbcTemplate = jdbcTemplateProvider.createJdbcTemplate(databaseProperties);
    }

    @Test
    void createJdbcTemplateMariaDB() {
        DatabaseProperties databaseProperties = new DatabaseProperties();

        databaseProperties.setDriver("org.mariadb.jdbc.Driver");
        databaseProperties.setHost(mariaDbContainer.getJdbcUrl());
        databaseProperties.setUsername(username);
        databaseProperties.setPassword(secret);
        JdbcTemplate jdbcTemplate = jdbcTemplateProvider.createJdbcTemplate(databaseProperties);
    }

}