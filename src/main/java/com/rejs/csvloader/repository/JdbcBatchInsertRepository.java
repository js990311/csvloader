package com.rejs.csvloader.repository;

import com.rejs.csvloader.repository.exception.NotFoundTypeSetterException;
import com.rejs.csvloader.repository.setter.JdbcTypeSetter;
import com.rejs.csvloader.yaml.properties.model.ColumnProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class JdbcBatchInsertRepository {
    private final List<JdbcTypeSetter> statementSetters;

    public void batchInsert(JdbcTemplate jdbcTemplate, String insertQuery, List<Object[]> datas, List<ColumnProperty> columns){
        int batchSize = 256;
        for(int i=0;i< datas.size();i +=batchSize){
            int end = Math.min(i+ batchSize, datas.size());
            insert(jdbcTemplate, insertQuery, datas.subList(i, end), columns);
        }
    }

    protected void insert(JdbcTemplate jdbcTemplate, String insertQuery, List<Object[]> datas, List<ColumnProperty> columns){
        jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Object[] data = datas.get(i);
                int idx = 0;
                for(ColumnProperty column : columns){
                    List<Integer> insertIndexes = column.getInsertIndexes() != null
                        ? column.getInsertIndexes()
                        : List.of(idx + 1)
                    ;
                    for(int insertIndex : insertIndexes){
                        if(data[idx] == null){
                            ps.setString(insertIndex+1, null);
                        }else {
                            String type = column.getType();
                            JdbcTypeSetter setter = getSetter(type);
                            setter.set(ps, insertIndex, data[idx]);
                        }
                    }
                    idx++;
                }
            }

            @Override
            public int getBatchSize() {
                return datas.size();
            }
        });
    }


    public JdbcTypeSetter getSetter(String type){
        for(JdbcTypeSetter setter: statementSetters){
            if(setter.support(type)){
                return setter;
            }
        }
        // TODO
        throw new NotFoundTypeSetterException(type);
    }

}
