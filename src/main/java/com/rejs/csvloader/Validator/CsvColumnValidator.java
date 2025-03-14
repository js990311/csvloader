package com.rejs.csvloader.Validator;

public interface CsvColumnValidator {
    Object validate(String column);

    boolean support(String type);

}
