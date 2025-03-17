package com.rejs.csvloader.repository;

import com.rejs.csvloader.TestcontainersConfiguration;
import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.repository.query.InsertQueryBuilder;
import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JdbcBatchInsertRepositoryTest {
    @Autowired
    private InsertQueryBuilder insertQueryBuilder;

    @Autowired
    private ImportPropertiesLoader loader;

    @Autowired
    private FileSystemAccessObject fao = new LocalFileSystemAccessObject();

    @Autowired
    private List<CsvColumnValidator> validators;

    @Autowired
    private JdbcBatchInsertRepository jdbcBatchInsertRepository;

    @Test
    void batchInsert() {

    }
}