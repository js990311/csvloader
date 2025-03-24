package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;

public class ConstantValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ColumnProperty property) {
        try {
            return property.getConstraints().getConstant();
        }catch (NullPointerException ex){
            throw new InvalidCsvColumnException("you must setting constant");
        }
    }

    @Override
    public boolean support(String type) {
        return "Constant".equals(type);
    }
}
