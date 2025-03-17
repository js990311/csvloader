package com.rejs.csvloader.validator;

public interface CsvColumnValidator {
    Object validate(String column);

    boolean support(String type);

}
