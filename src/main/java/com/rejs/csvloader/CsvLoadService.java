package com.rejs.csvloader;

import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.repository.JdbcBatchInsertRepository;
import com.rejs.csvloader.repository.JdbcTemplateProvider;
import com.rejs.csvloader.repository.query.InsertQueryBuilder;
import com.rejs.csvloader.validator.CsvColumnValidateService;
import com.rejs.csvloader.validator.CsvColumnValidationResult;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import com.rejs.csvloader.yaml.properties.model.WorkProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CsvLoadService {
    private final InsertQueryBuilder insertQueryBuilder;
    private final CsvColumnValidateService validateService;
    private final JdbcBatchInsertRepository jdbcBatchInsertRepository;
    private final JdbcTemplateProvider jdbcTemplateProvider;

    public void load(ImportProperties properties, Resource data){
        JdbcTemplate jdbcTemplate = jdbcTemplateProvider.createJdbcTemplate(properties.getDatabase());

        for(WorkProperty work : properties.getWorks()){
            String query = insertQueryBuilder.buildInsertQuery(work);

            List<ColumnProperty> columns = work.getColumns();
            CsvColumnValidationResult result = validateService.validate(columns, data);

            List<Object[]> datas = result.getValidDatas();
            jdbcBatchInsertRepository.batchInsert(jdbcTemplate, query, datas, columns);
        }
    }
}
