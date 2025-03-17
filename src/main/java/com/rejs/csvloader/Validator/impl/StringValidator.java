package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ImportColumn;
import org.springframework.stereotype.Component;

@Component
public class StringValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ImportColumn property) {
        if(!property.isNullable() && column == null){
            throw new InvalidCsvColumnException("column must not null");
        }
        return column;
    }

    @Override
    public boolean support(String type) {
        return type.equals("String");
    }
}
