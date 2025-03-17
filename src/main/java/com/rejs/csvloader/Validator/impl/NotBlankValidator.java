package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import org.springframework.stereotype.Component;

@Component
public class NotBlankValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ColumnProperty property) {
        if(column != null && !column.isBlank()){
            return column;
        }else if (!property.isNullable()){
            throw new InvalidCsvColumnException("Column is Blank");
        }else {
            return null;
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("NotBlank");
    }
}
