package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ColumnProperty property) {
        if(column != null && !column.isBlank() && column.contains("@")){
            return column;
        }else if (property.isNullable() && column == null){
            return null;
        }else {
            throw new InvalidCsvColumnException("column is not email");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("Email");
    }
}
