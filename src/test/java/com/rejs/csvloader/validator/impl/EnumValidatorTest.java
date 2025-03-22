package com.rejs.csvloader.validator.impl;

import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import com.rejs.csvloader.yaml.properties.model.ConstraintsProperty;
import com.rejs.csvloader.yaml.properties.model.EnumProperty;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnumValidatorTest {

    @Test
    void validate() {
        EnumValidator enumValidator = new EnumValidator();

        String male = "1";
        String female = "2";
        String other = "3";
        ColumnProperty columnProperty = new ColumnProperty();
        ConstraintsProperty constraintsProperty = new ConstraintsProperty();
        EnumProperty enumProperty = new EnumProperty();
        enumProperty.setMappings(Map.of("1", "Male", "2", "Female"));
        constraintsProperty.setEnums(enumProperty);
        columnProperty.setConstraints(constraintsProperty);


        String maleValidate = (String) enumValidator.validate(male, columnProperty);
        String femaleValidate = (String) enumValidator.validate(female, columnProperty);
        Object otherValidate = enumValidator.validate(other, columnProperty);

        assertEquals("Male", maleValidate);
        assertEquals("Female", femaleValidate);
        assertNull(otherValidate);
    }
}