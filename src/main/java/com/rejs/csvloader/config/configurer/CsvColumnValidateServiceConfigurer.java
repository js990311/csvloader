package com.rejs.csvloader.config.configurer;

import com.rejs.csvloader.repository.setter.JdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.IntegerJdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.LongJdbcTypeSetter;
import com.rejs.csvloader.repository.setter.impl.StringJdbcTypeSetter;
import com.rejs.csvloader.validator.CsvColumnValidateService;
import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.impl.IntegerValidator;
import com.rejs.csvloader.validator.impl.LongValidatior;
import com.rejs.csvloader.validator.impl.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class CsvColumnValidateServiceConfigurer implements CsvLoadConfigurer{
    private List<CsvColumnValidator> validators = new ArrayList<>();

    public CsvColumnValidateServiceConfigurer custom(CsvColumnValidator... validators){
        this.validators.addAll(List.of(validators));
        return this;
    }


    public CsvColumnValidateServiceConfigurer withDefault(){
        validators.addAll(List.of(
                new LongValidatior(),
                new IntegerValidator(),
                new StringValidator()
        ));
        return this;
    }


    @Override
    public void apply(CsvLoadBuilder builder) {
        builder.setCsvColumnValidateService(new CsvColumnValidateService(validators));
    }
}
