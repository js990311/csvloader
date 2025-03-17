package com.rejs.csvloader.repository.setter.impl;

import com.rejs.csvloader.repository.setter.JdbcTypeSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class IntegerJdbcTypeSetter implements JdbcTypeSetter {
    @Override
    public void set(PreparedStatement ps, int index, Object o) throws SQLException {
        ps.setInt(index, (Integer) o);
    }

    @Override
    public boolean support(String type) {
        return type.equals("Integer");
    }

}
