package com.rejs.csvloader.Validator.impl;

import com.rejs.csvloader.Validator.CsvColumnValidator;
import com.rejs.csvloader.Validator.exception.InvalidCsvColumnException;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column) {
        if(column != null && !column.isBlank() && column.contains("@")){
            return column;
        }else {
            throw new InvalidCsvColumnException("column is not email");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("Email");
    }
}
