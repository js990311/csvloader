package com.rejs.csvloader.validator;

import com.rejs.csvloader.validator.exception.InvalidCsvColumnException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvColumnValidatorTest {

    @Autowired private List<CsvColumnValidator> validators;

    @DisplayName("csvColumnValidator의 validator support 검증")
    @ParameterizedTest
    @CsvSource({
            "NotBlank, name",
            "Email, test@example.com",
            "PositiveInteger, 21"
    })
    void validate(String type, String column) {
        boolean isFound = false;
        for(CsvColumnValidator validator : validators){
            if(validator.support(type)){
                Object validate = validator.validate(column);
                isFound = true;
                break;
            }
        }
        if(!isFound){
            fail("validator를 찾지 못했습니다." + type);
        }
    }

    @DisplayName("csvColumnValidator의 validator의 inValid 검증")
    @ParameterizedTest
    @CsvSource({
            "NotBlank, ",
            "Email, example.com",
            "PositiveInteger, -1"
    })
    void validateWithInvalidData(String type, String column) {
        boolean isFound = false;
        for(CsvColumnValidator validator : validators){
            if(validator.support(type)){
                assertThrows(InvalidCsvColumnException.class, () -> {
                    Object validate = validator.validate(column);
                });
                isFound = true;
                break;
            }
        }
        if(!isFound){
            fail("validator를 찾지 못했습니다." + type);
        }
    }


}