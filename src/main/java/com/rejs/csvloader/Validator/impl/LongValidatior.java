package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ImportColumn;
import org.springframework.stereotype.Component;

@Component
public class LongValidatior implements CsvColumnValidator {

    @Override
    public Object validate(String column, ImportColumn property) {
        try {
            long number = Long.parseLong(column);
            return number;
        }catch (RuntimeException e){
            if(property.isNullable() && column == null){
                return null;
            }
            throw new InvalidCsvColumnException("column is not number");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("Long");
    }
}
