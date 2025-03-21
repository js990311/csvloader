package com.rejs.csvloader.config;

import com.rejs.csvloader.CsvLoadService;
import com.rejs.csvloader.config.configurer.CsvLoadBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCsvLoadConfigurationTest {

    @Test
    void csvLoadBuilder() {
        CsvLoadConfiguration csvLoadConfiguration = new CsvLoadConfiguration();
        CsvLoadService csvLoadService = csvLoadConfiguration.csvLoadBuilder(new CsvLoadBuilder()).build();

    }
    
    static class CsvLoadConfiguration extends AbstractCsvLoadConfiguration { }
}