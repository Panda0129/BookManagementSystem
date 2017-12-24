package com.library.dao;

import com.library.domain.ReaderCard;
import com.library.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class ReaderInfoDao {
    private JdbcTemplate jdbcTemplate;
    private final static String SHOW_READER_INFO = "select * from reader_info " +
                                                    "where reader_id = ?";
    private final static String ALTER_READER_INFO = "update reader_info set name = ?, sex = ?, address = ?, birth = ?, telcode = ?" +
            " where reader_id = ?";
    private final static String ALL_READER_INFO_SQL = "select * from reader_info";
    private final static String MATCH_READER_SQL = "select count(*) from reader_info where reader_id like ? or name like ?";
    private final static String QUERY_READER_INFO_SQL = "select * from reader_info where reader_id like ? or name like ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int matchReader(String input) {
        String search = "%" + input + "%";
        return jdbcTemplate.queryForObject(MATCH_READER_SQL, new Object[] {search, search}, Integer.class);
    }

    public ArrayList<ReaderInfo> queryReader (String input) {
        String search = "%" + input + "%";
        final ArrayList<ReaderInfo> readerInfos = new ArrayList<ReaderInfo>();
        jdbcTemplate.query(QUERY_READER_INFO_SQL, new Object[]{search, search}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    ReaderInfo readerInfo = new ReaderInfo();
                    readerInfo.setId(resultSet.getString("reader_id"));
                    readerInfo.setName(resultSet.getString("name"));
                    readerInfo.setSex(resultSet.getString("sex"));
                    readerInfo.setBirth(resultSet.getDate("birth"));
                    readerInfo.setAddress(resultSet.getString("address"));
                    readerInfo.setTelcode(resultSet.getString("telcode"));
                    readerInfos.add(readerInfo);
                }
            }
        });
        return readerInfos;
    }

    public ReaderInfo showReaderInfo(final String reader_id) {

        final ReaderInfo readerInfo = new ReaderInfo();

        jdbcTemplate.query(SHOW_READER_INFO, new Object[]{reader_id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                readerInfo.setId(resultSet.getString("reader_id"));
                readerInfo.setName(resultSet.getString("name"));
                readerInfo.setSex(resultSet.getString("sex"));
                readerInfo.setTelcode(resultSet.getString("telcode"));
                readerInfo.setAddress(resultSet.getString("address"));
                readerInfo.setBirth(resultSet.getDate("birth"));
            }
        });

        return readerInfo;
    }

    public int modifyIndividualInfo(ReaderInfo readerInfo) {
//        System.out.println(readerInfo.getAddress() + " " + readerInfo.getId());
        return  jdbcTemplate.update(ALTER_READER_INFO, readerInfo.getName(), readerInfo.getSex(), readerInfo.getAddress(),
                                    readerInfo.getBirth(), readerInfo.getTelcode(), readerInfo.getId());
    }

    public ArrayList<ReaderInfo> showAllReaderInfo () {
        final ArrayList<ReaderInfo> readerInfos = new ArrayList<ReaderInfo>();

        jdbcTemplate.query(ALL_READER_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while(resultSet.next()) {
                    ReaderInfo readerInfo = new ReaderInfo();
                    readerInfo.setId(resultSet.getString("reader_id"));
                    readerInfo.setName(resultSet.getString("name"));
                    readerInfo.setSex(resultSet.getString("sex"));
                    readerInfo.setTelcode(resultSet.getString("telcode"));
                    readerInfo.setAddress(resultSet.getString("address"));
                    readerInfo.setBirth(resultSet.getDate("birth"));
                    readerInfos.add(readerInfo);
                }
            }
        });
        return readerInfos;
    }
}
