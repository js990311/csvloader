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

import static org.junit.jupiter.api.Assertions.*;

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

        CsvColumnValidationResult validate = validateService.validate(properties.getWorks().get(0).getColumns(), data);
        assertNotNull(validate);
        assertEquals(5, validate.getValidDatas().size());
        assertEquals(3, validate.getInValidData().size());

    }
}