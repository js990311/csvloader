package com.rejs.csvloader.config;

import com.rejs.csvloader.CsvLoadCommandLineRunner;
import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CsvLoadCommandLineRunner 빈 등록
 */
@Configuration
public class AbstractCsvLoadCommandLineRunnerConfiguration extends AbstractCsvLoadConfiguration{

    @Bean
    public CsvLoadCommandLineRunner csvLoadCommandLineRunner(){
        return new CsvLoadCommandLineRunner(csvLoadService(), importPropertiesLoader(), fileSystemAccessObject());
    }

    @Bean
    public FileSystemAccessObject fileSystemAccessObject(){
        return new LocalFileSystemAccessObject();
    }

    @Bean
    public ImportPropertiesLoader importPropertiesLoader(){
        return new ImportPropertiesLoader(fileSystemAccessObject());
    }

}
