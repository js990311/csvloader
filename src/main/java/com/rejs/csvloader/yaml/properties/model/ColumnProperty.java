package com.rejs.csvloader.yaml.properties.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ColumnProperty {
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
    private Boolean nullable;
    private ConstraintsProperty constraints;

    /**
     * InsertQuery가 존재할 경우에만 사용됨
     */
    private List<Integer> insertIndexes;

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isNullable() {
        return nullable == null || nullable;
    }
}
