package com.rejs.csvloader.yaml.properties.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConstraintsProperty {
    private String regex;
    private Long min;
    private Long max;
    private EnumProperty enums;
    private String constant;
}
