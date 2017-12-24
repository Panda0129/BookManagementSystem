package com.library.dao;

import com.library.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminDao {
    private JdbcTemplate jdbcTemplate;
    private final static String COUNT_ADMIN_SQL = "select count(*) from admin where admin_id = ? and password = ?";
    private final static String SELECT_ADMIN_SQL = "select admin_id, password from admin where admin_id = ?";

    @Autowired
    public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String admin_id, String admin_pwd) {
        return jdbcTemplate.queryForObject(COUNT_ADMIN_SQL, new Object[] {admin_id, admin_pwd}, Integer.class);
    }

    public Admin findAdminById(final String admin_id) {
        final Admin admin = new Admin();

        jdbcTemplate.query(SELECT_ADMIN_SQL, new Object[]{admin_id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                admin.setAdmin_id(resultSet.getString("admin_id"));
                admin.setAdmin_pwd(resultSet.getString("password"));
            }
        });

        return admin;
    }
}
