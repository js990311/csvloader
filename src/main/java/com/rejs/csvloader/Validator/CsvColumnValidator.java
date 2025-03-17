package com.rejs.csvloader.validator;

import com.rejs.csvloader.yaml.properties.model.ImportColumn;

public interface CsvColumnValidator {
    Object validate(String column, ImportColumn property);

    boolean support(String type);

}
