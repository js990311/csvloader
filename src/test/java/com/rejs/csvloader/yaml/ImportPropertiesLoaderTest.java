package com.rejs.csvloader.yaml;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.yaml.exception.YamlPropertiesLoadFailException;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import com.rejs.csvloader.yaml.properties.model.WorkProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportPropertiesLoaderTest {
    private final FileSystemAccessObject fileSAO = new LocalFileSystemAccessObject();
    private final ImportPropertiesLoader importPropertiesLoader = new ImportPropertiesLoader(fileSAO);

    @Test
    void loadProperties() {
        ImportProperties properties = importPropertiesLoader.loadProperties("test.yml");
        assertNotNull(properties);

        assertEquals(1, properties.getWorks().size());

        WorkProperty work = properties.getWorks().get(0);
        assertEquals("test_users", work.getTableName());
        assertEquals(4, work.getColumns().size());

        assertEquals(false, work.getColumns().get(0).isNullable());
        assertEquals(true, work.getColumns().get(3).isNullable());

        assertNotNull(work.getColumns().get(1).getConstraints().getRegex());

        assertNotNull(work.getColumns().get(2).getConstraints().getMin());
        assertEquals(1, work.getColumns().get(2).getConstraints().getMin());

        assertEquals("jdbc:postgresql://localhost:5432/mydatabase", properties.getDatabase().getHost());
        assertEquals("myuser", properties.getDatabase().getUsername());
        assertEquals("secret", properties.getDatabase().getPassword());
        assertEquals("org.postgresql.Driver", properties.getDatabase().getDriver());
    }

    @Test
    void loadWrongProperties() {
        assertThrows(YamlPropertiesLoadFailException.class, () -> {
            importPropertiesLoader.loadProperties("wrong.yml");
        });
    }

}