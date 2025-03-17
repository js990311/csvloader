package com.rejs.csvloader.repository.setter.impl;

import com.rejs.csvloader.repository.setter.JdbcTypeSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class StringJdbcTypeSetter implements JdbcTypeSetter {
    @Override
    public void set(PreparedStatement ps, int index, Object o) throws SQLException {
        if(o == null){
            return;
        }
        ps.setString(index, (String) o);
    }

    @Override
    public boolean support(String type) {
        return type.equals("STRING");
    }
}
