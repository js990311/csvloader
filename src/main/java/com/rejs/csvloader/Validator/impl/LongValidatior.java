package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import org.springframework.stereotype.Component;

@Component
public class LongValidatior implements CsvColumnValidator {

    @Override
    public Object validate(String column, ColumnProperty property) {
        try {
            long number = Long.parseLong(column);
            if(property.getConstraints() != null) {
                if(property.getConstraints().getMax() != null && number>property.getConstraints().getMax()){
                    throw new InvalidCsvColumnException("invalid number range");
                }
                if(property.getConstraints().getMin() != null && number<property.getConstraints().getMin()){
                    throw new InvalidCsvColumnException("invalid number range");
                }
            }
            return number;
        }catch (NumberFormatException e){
            if(property.isNullable() && column == null){
                return null;
            }
            throw new InvalidCsvColumnException("column is not number");
        }catch (RuntimeException exception){
            throw new InvalidCsvColumnException(exception);
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("Long");
    }
}
