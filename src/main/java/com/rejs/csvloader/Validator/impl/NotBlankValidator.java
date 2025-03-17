package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
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
