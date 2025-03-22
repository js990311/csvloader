package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.validator.CsvColumnValidator;
import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import com.rejs.csvloader.yaml.properties.model.EnumProperty;
import org.springframework.stereotype.Component;

@Component
public class EnumValidator implements CsvColumnValidator {

    @Override
    public Object validate(String column, ColumnProperty property) {
        EnumProperty enums = null;
        try {
            enums = property.getConstraints().getEnums();
        }catch (NullPointerException e){
            throw new InvalidCsvColumnException("There is no enum properties");
        }
        try {
            if(enums.getMappings().containsKey(column)){
                 return enums.getMappings().get(column);
            }else if(property.isNullable()){
                return null;
            }else {
                throw new InvalidCsvColumnException("there is no enum mapper");
            }

        }catch (RuntimeException ex){
            throw new InvalidCsvColumnException(ex);
        }
    }

    @Override
    public boolean support(String type) {
        return "Enum".equals(type);
    }
}
