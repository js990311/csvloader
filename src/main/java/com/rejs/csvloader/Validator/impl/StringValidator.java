package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import org.springframework.stereotype.Component;

@Component
public class StringValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column) {
        return column;
    }

    @Override
    public boolean support(String type) {
        return type.equals("String");
    }
}
