package com.rejs.csvloader.yaml.properties.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkProperty {
    private String tableName;
    /**
     * InsertQuery를 별도 생성하지 않고 수동으로 지정하는 경우
     */
    private String insertQuery;
    private List<ColumnProperty> columns;
}
