package com.rejs.csvloader.repository.query;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertQueryBuilderTest {
    private FileSystemAccessObject fileSAO = new LocalFileSystemAccessObject();
    private ImportPropertiesLoader loader = new ImportPropertiesLoader(fileSAO);
    private InsertQueryBuilder importQueryBuilder = new InsertQueryBuilder();

    @Test
    void buildInsertQuery() {
        ImportProperties properties = loader.loadProperties("test.yml");
        String query = importQueryBuilder.buildInsertQuery(properties.getWorks().get(0));
        assertNotEquals("INSERT test_users(name, email, age, test_korean) VALUES (?, ?, ?, ?);", query);
    }

    @Test
    void buildInsertQueryWithQueryProperty(){
        ImportProperties properties = loader.loadProperties("test2.yml");
        String query = importQueryBuilder.buildInsertQuery(properties.getWorks().get(0));
        assertEquals("INSERT test_points(name, longitude, latitude, lng, lat) VALUES (?,?,?,?,?)", query);
    }
}