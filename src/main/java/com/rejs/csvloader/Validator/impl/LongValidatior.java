package com.rejs.csvloader.Validator.impl;

import com.rejs.csvloader.Validator.CsvColumnValidator;
import com.rejs.csvloader.Validator.exception.InvalidCsvColumnException;
import org.springframework.stereotype.Component;

@Component
public class LongValidatior implements CsvColumnValidator {

    @Override
    public Object validate(String column) {
        try {
            long number = Long.parseLong(column);
            return number;
        }catch (RuntimeException e){
            throw new InvalidCsvColumnException("column is not number");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("Long");
    }
}
