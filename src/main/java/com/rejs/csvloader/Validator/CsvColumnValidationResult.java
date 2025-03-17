package com.rejs.csvloader.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CsvColumnValidationResult {
    private final List<Object[]> validDatas;
    private final List<String[]> inValidData;

    public void addValidData(Object[] data){
        validDatas.add(data);
    }

    public void addInvalidData(String[] data){
        inValidData.add(data);
    }

    public CsvColumnValidationResult() {
        this.validDatas = new ArrayList<>();
        this.inValidData = new ArrayList<>();
    }
}
