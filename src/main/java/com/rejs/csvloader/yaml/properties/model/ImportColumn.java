package com.rejs.csvloader.yaml.properties.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ImportColumn {
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
    private Boolean nullable;

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isNullable() {
        return nullable == null || nullable;
    }
}
