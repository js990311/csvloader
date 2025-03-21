package com.rejs.csvloader.config;

import com.rejs.csvloader.CsvLoadService;
import com.rejs.csvloader.config.configurer.CsvColumnValidateServiceConfigurer;
import com.rejs.csvloader.config.configurer.CsvLoadBuilder;
import com.rejs.csvloader.config.configurer.JdbcBatchInsertRepositoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class AbstractCsvLoadConfiguration {

    public CsvLoadBuilder csvLoadBuilder(CsvLoadBuilder builder){
        return builder
            .jdbcBatchInsertRepository(
                new JdbcBatchInsertRepositoryConfigurer().withDefault()
        ).csvColumnValidateService(
            new CsvColumnValidateServiceConfigurer().withDefault()
        );
    }

    @Bean
    public CsvLoadService csvLoadService(){
        return csvLoadBuilder(new CsvLoadBuilder()).build();
    }
}
