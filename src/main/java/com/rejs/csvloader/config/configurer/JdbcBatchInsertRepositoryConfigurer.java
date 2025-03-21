package com.rejs.csvloader.config.configurer;

import com.rejs.csvloader.repository.JdbcBatchInsertRepository;
import com.rejs.csvloader.repository.setter.JdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.IntegerJdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.LongJdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.StringJdbcTypeSetter;
import com.rejs.csvloader.validator.impl.IntegerValidator;
import com.rejs.csvloader.validator.impl.LongValidatior;
import com.rejs.csvloader.validator.impl.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class JdbcBatchInsertRepositoryConfigurer implements CsvLoadConfigurer{
    private List<JdbcTypeSetter> setters = new ArrayList<>();

    public JdbcBatchInsertRepositoryConfigurer withDefault(){
        setters.addAll(List.of(
                new LongJdbcTypeSetter(),
                new IntegerJdbcTypeSetter(),
                new StringJdbcTypeSetter()
        ));
        return this;
    }

    @Override
    public void apply(CsvLoadBuilder builder) {
        JdbcBatchInsertRepository jdbcBatchInsertRepository = new JdbcBatchInsertRepository(setters);
        builder.setInsertQueryBuilder(jdbcBatchInsertRepository);
    }
}
