package com.rejs.csvloader.repository;

import com.rejs.csvloader.yaml.properties.config.DatabaseProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcTemplateProvider {
    public JdbcTemplate createJdbcTemplate(DatabaseProperties database){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(database.getHost());
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.setDriverClassName(database.getDriver());

        DataSource dataSource = new HikariDataSource(config);
        return new JdbcTemplate(dataSource);
    }
}
