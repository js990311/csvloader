package com.rejs.csvloader.validator;

import com.rejs.csvloader.yaml.properties.model.ColumnProperty;

public interface CsvColumnValidator {
    Object validate(String column, ColumnProperty property);

    boolean support(String type);

}
