package com.rejs.csvloader.yaml;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.yaml.exception.YamlPropertiesLoadFailException;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import com.rejs.csvloader.yaml.properties.model.WorkProperty;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        // 테이블네임이 제대로 되는지
        assertEquals("test_users", work.getTableName());

        // 5개의 컬럼이 다 있는지
        assertEquals(5, work.getColumns().size());

        // nullable 프로퍼티 검사
        assertEquals(false, work.getColumns().get(0).isNullable());
        assertEquals(true, work.getColumns().get(3).isNullable());

        // regex 제약조건이 있는지
        assertNotNull(work.getColumns().get(1).getConstraints().getRegex());

        // min값 속성이 제대로 저장되는지
        assertNotNull(work.getColumns().get(2).getConstraints().getMin());
        assertEquals(1, work.getColumns().get(2).getConstraints().getMin());

        // enumMappings가 제대로 있는지
        assertNotNull(work.getColumns().get(4).getConstraints().getEnums());
        assertEquals(2, work.getColumns().get(4).getConstraints().getEnums().getMappings().keySet().size());

        // database 속성이 제대로 저장되는지
        assertEquals("jdbc:postgresql://localhost:5432/mydatabase", properties.getDatabase().getHost());
        assertEquals("myuser", properties.getDatabase().getUsername());
        assertEquals("secret", properties.getDatabase().getPassword());
        assertEquals("org.postgresql.Driver", properties.getDatabase().getDriver());
    }

    @Test
    void loadPropertiesWithInsertQuery() {
        ImportProperties properties = importPropertiesLoader.loadProperties("test2.yml");
        WorkProperty work = properties.getWorks().get(0);
        assertNotNull(work.getInsertQuery());
        assertEquals("INSERT test_points(name, longitude, latitude, lng, lat) VALUES (?,?,?,?,?)", work.getInsertQuery());

        List<Integer> insertIndexes = work.getColumns().get(2).getInsertIndexes();
        assertEquals(2, insertIndexes.size());
    }

    @Test
    void loadWrongProperties() {
        assertThrows(YamlPropertiesLoadFailException.class, () -> {
            importPropertiesLoader.loadProperties("wrong.yml");
        });
    }

}