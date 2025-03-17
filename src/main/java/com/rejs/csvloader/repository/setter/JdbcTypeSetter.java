package com.rejs.csvloader.repository.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * PreparedStatementSetter는 이미 있어서 혼동의 여지를 막기위해
 */
public interface JdbcTypeSetter {
    public void set(PreparedStatement ps, int index, Object o) throws SQLException;
    public boolean support(String type);
}
