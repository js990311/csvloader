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
    private List<ColumnProperty> columns;
    /**
     * InsertQuery를 별도 생성하지 않고 수동으로 지정하는 경우
     */
    private String insertQuery;
    /**
     * Insert 쿼리 이외에도 단순 쿼리를 실행할 필요가 있는 경우
     */
    private String executeQuery;

}
