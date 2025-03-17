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
import com.rejs.csvloader.yaml.properties.model.ImportColumn;
import com.rejs.csvloader.yaml.properties.model.ImportWork;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CsvLoadService implements CommandLineRunner {
    private final InsertQueryBuilder insertQueryBuilder;
    private final ImportPropertiesLoader loader;
    private final FileSystemAccessObject fao = new LocalFileSystemAccessObject();
    private final CsvColumnValidateService validateService;
    private final JdbcBatchInsertRepository jdbcBatchInsertRepository;
    private final JdbcTemplateProvider jdbcTemplateProvider;

    public void load(String yml, String csv){
        ImportProperties properties = loader.loadProperties(yml);
        Resource data = fao.load(csv);
        JdbcTemplate jdbcTemplate = jdbcTemplateProvider.createJdbcTemplate(properties.getDatabase());

        for(ImportWork work : properties.getWorks()){
            String query = insertQueryBuilder.buildInsertQuery(work);

            List<ImportColumn> columns = work.getColumns();
            CsvColumnValidationResult result = validateService.validate(columns, data);

            List<Object[]> datas = result.getValidDatas();
            jdbcBatchInsertRepository.batchInsert(jdbcTemplate, query, datas, columns);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length < 2){
            throw new IllegalArgumentException("we need 2 parameter");
        }

        String ymlPath = args[0];
        String csvPath = args[1];

        load(ymlPath, csvPath);
    }
}
