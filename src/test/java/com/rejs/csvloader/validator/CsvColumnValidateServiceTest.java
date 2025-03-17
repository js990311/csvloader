package com.rejs.csvloader.validator;

import com.rejs.csvloader.TestcontainersConfiguration;
import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CsvColumnValidateServiceTest {
    @Autowired
    private CsvColumnValidateService validateService;
    @Autowired private ImportPropertiesLoader loader;
    @Autowired private FileSystemAccessObject fao;


    @Test
    void validate() {
        ImportProperties properties = loader.loadProperties("test.yml");
        Resource data = fao.load("test.csv");

        CsvColumnValidationResult result = validateService.validate(properties.getWorks().get(0).getColumns(), data);
        assertNotNull(result);
        assertEquals(6, result.getValidDatas().size());
        assertEquals(3, result.getInValidData().size());

    }
}