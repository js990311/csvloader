package com.rejs.csvloader.Validator.impl;

import com.rejs.csvloader.Validator.CsvColumnValidator;
import com.rejs.csvloader.Validator.exception.InvalidCsvColumnException;
import org.springframework.stereotype.Component;

@Component
public class PositiveIntegerValidator implements CsvColumnValidator {
    @Override
    public Object validate(String column) {
        try{
            int number = Integer.parseInt(column);
            if(number<=0){
                throw new InvalidCsvColumnException("column is not positive integer");
            }else {
                return number;
            }
        }catch (NumberFormatException e){
            throw new InvalidCsvColumnException("column is not number");
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("PositiveInteger");
    }
}
