package com.rejs.csvloader.Validator.impl;

import com.rejs.csvloader.Validator.CsvColumnValidator;
import com.rejs.csvloader.Validator.exception.InvalidCsvColumnException;
import org.springframework.stereotype.Component;

@Component
public class NotBlankValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column) {
        if(column != null && !column.isBlank()){
            return column;
        }else {
            throw new InvalidCsvColumnException("Column is Blank");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("NotBlank");
    }
}
