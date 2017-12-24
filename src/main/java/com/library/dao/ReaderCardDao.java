package com.library.dao;


import com.library.domain.ReaderCard;
import javafx.beans.binding.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class ReaderCardDao {
    private JdbcTemplate jdbcTemplate;
    private final static String COUNT_READER_SQL = "select count(*) from reader_card " +
            "where reader_id = ? and passwd = ?";
    private final static String CHANGE_PASSWORD_SQL = "update reader_card set passwd = ? where reader_id = ?";
    private final static String ADD_READER_CARD_SQL = "insert into reader_card (reader_id, name, passwd, card_state) values (?, ?, ?, ?)";
    private final static String COUNT_ACCOUNT_SQL = "select count(*) from reader_card where reader_id = ?";
    private final static String ADD_READER_CARD_INTO_INFO_SQL = "insert into reader_info (reader_id, name) values (?, ?)";
    private final static String SHOW_ALL_READER_CARD_SQL = "select * from reader_card";
    private final static String DELETE_READER_CARD_SQL = "delete from reader_card where reader_id = ?";
    private final static String DELETE_INFO_SQL = "delete from reader_info where reader_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String reader_id, String pwd) {
        return jdbcTemplate.queryForObject(COUNT_READER_SQL, new Object[] {reader_id, pwd}, Integer.class);
    }

    public int hasMatchAccount(String reader_id) {
        return jdbcTemplate.queryForObject(COUNT_ACCOUNT_SQL, new Object[] {reader_id}, Integer.class);
    }

    public void deleteReaderCard (String reader_id) {
        jdbcTemplate.update(DELETE_INFO_SQL, reader_id);
        jdbcTemplate.update(DELETE_READER_CARD_SQL, reader_id);
    }

    public ReaderCard findReaderById(final String reader_id) {
        String sql = "select * " +
                "from reader_card where reader_id = ?";
        final ReaderCard readerCard = new ReaderCard();
        jdbcTemplate.query(sql, new Object[]{reader_id},
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        readerCard.setReader_id(resultSet.getString("reader_id"));
                        readerCard.setName(resultSet.getString("name"));
                        readerCard.setPwd(resultSet.getString("passwd"));
                        readerCard.setState(resultSet.getInt("card_state"));
                    }
                });
        return readerCard;
    }

    public ArrayList<ReaderCard> showAllReaderCard() {
        final ArrayList<ReaderCard> readerCards = new ArrayList<ReaderCard>();
        jdbcTemplate.query(SHOW_ALL_READER_CARD_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    ReaderCard readerCard = new ReaderCard();
                    readerCard.setState(resultSet.getInt("card_state")); // 1:normal 2:vip 3:svip
                    readerCard.setName(resultSet.getString("name"));
                    readerCard.setReader_id(resultSet.getString("reader_id"));
                    readerCards.add(readerCard);
                }
            }
        });
        return readerCards;
    }

    public int rePassword(String reader_id, String new_pwd) {
        return jdbcTemplate.update(CHANGE_PASSWORD_SQL, new_pwd, reader_id);
    }

    public int addReaderCard(ReaderCard readerCard) {
        String reader_id = readerCard.getReader_id();
        String name = readerCard.getName();
        String pwd = readerCard.getPwd();
        int state = 1;
        System.out.println(reader_id + " " + name+ " " + pwd);
        jdbcTemplate.update(ADD_READER_CARD_INTO_INFO_SQL, reader_id, name);
        return jdbcTemplate.update(ADD_READER_CARD_SQL, reader_id, name, pwd, state);
    }

}
