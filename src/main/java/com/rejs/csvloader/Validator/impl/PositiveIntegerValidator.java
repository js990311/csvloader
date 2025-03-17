package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import org.springframework.stereotype.Component;

@Component
public class PositiveIntegerValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column, ColumnProperty property) {
        try{
            int number = Integer.parseInt(column);
            if(number<=0){
                throw new InvalidCsvColumnException("column is not positive integer");
            }else {
                return number;
            }
        }catch (NumberFormatException e){
            if(property.isNullable() && column == null){
                return null;
            }
            throw new InvalidCsvColumnException("column is not number");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("PositiveInteger");
    }
}
