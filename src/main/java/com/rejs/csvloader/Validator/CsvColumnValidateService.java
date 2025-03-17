package com.rejs.csvloader.validator;

import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import com.rejs.csvloader.yaml.properties.model.ImportColumn;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CsvColumnValidateService {
    private final List<CsvColumnValidator> validators;

    private CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
    }

    public CsvColumnValidationResult validate(List<ImportColumn> columns, Resource resource){
        CsvParser parser = getCsvParser();
        CsvColumnValidationResult result = new CsvColumnValidationResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){
            for(String[] row : parser.iterate(reader)){
                try { // 현재 행이 추출되고 있는가?
                    int idx = 0;
                    Object[] objects = new Object[columns.size()];
                    for(ImportColumn column : columns){
                        String type = column.getType();
                        if(column.getValidationType() != null){
                            type = column.getValidationType();
                        }
                        objects[idx] = extract(row[column.getCsvIndex()], type);
                        idx++;
                    }
                    result.addValidData(objects);
                }catch (InvalidCsvColumnException ex){
                    // 현재 행을 추출하는 데 실패함
                    result.addInvalidData(row);
                }catch (RuntimeException ex){
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            // 아예 CSV 파일을 import하는데 실패함
            throw new RuntimeException(e);
        }
        return result;
    }

    private Object extract(String s, String type) {
        for (CsvColumnValidator validator : validators){
            if(validator.support(type)){
                return validator.validate(s);
            }
        }
        throw new InvalidCsvColumnException();
    }

}
