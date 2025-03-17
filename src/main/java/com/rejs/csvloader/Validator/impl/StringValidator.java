package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ColumnProperty property) {
        if(!property.isNullable() && column == null){
            throw new InvalidCsvColumnException("column must not null");
        }
        if(property.getConstraints() != null){
            String regex = property.getConstraints().getRegex();
            if(regex != null){
                Pattern pattern = Pattern.compile(regex);
                if(!pattern.matcher(column).matches()){
                    throw new InvalidCsvColumnException("Invalid value for column(not match regex)");
                }
            }
        }
        return column;
    }

    @Override
    public boolean support(String type) {
        return type.equals("String");
    }
}
