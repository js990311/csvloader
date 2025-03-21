package com.rejs.csvloader.config.configurer;

import com.rejs.csvloader.CsvLoadService;
import com.rejs.csvloader.repository.JdbcBatchInsertRepository;
import com.rejs.csvloader.repository.JdbcTemplateProvider;
import com.rejs.csvloader.repository.query.InsertQueryBuilder;
import com.rejs.csvloader.validator.CsvColumnValidateService;

public class CsvLoadBuilder {
    private final InsertQueryBuilder insertQueryBuilder;
    private final JdbcTemplateProvider jdbcTemplateProvider;
    private JdbcBatchInsertRepository jdbcBatchInsertRepository;
    private CsvColumnValidateService csvColumnValidateService;

    public CsvLoadBuilder() {
        this.insertQueryBuilder = new InsertQueryBuilder();
        this.jdbcTemplateProvider = new JdbcTemplateProvider();
    }

    public CsvLoadBuilder jdbcBatchInsertRepository(JdbcBatchInsertRepositoryConfigurer configurer){
        configurer.apply(this);
        return this;
    }

    public CsvLoadBuilder csvColumnValidateService(CsvColumnValidateServiceConfigurer configurer){
        configurer.apply(this);
        return this;
    }

    protected void setInsertQueryBuilder(JdbcBatchInsertRepository jdbcBatchInsertRepository){
        this.jdbcBatchInsertRepository = jdbcBatchInsertRepository;
    }

    protected void setCsvColumnValidateService(CsvColumnValidateService validateService){
        this.csvColumnValidateService = validateService;
    }

    public CsvLoadService build(){
        return new CsvLoadService(insertQueryBuilder, csvColumnValidateService, jdbcBatchInsertRepository, jdbcTemplateProvider);
    }
}
