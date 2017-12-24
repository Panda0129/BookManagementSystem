package com.library.dao;

import com.library.domain.LendList;
import com.library.domain.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Repository
public class LendDao {

    private JdbcTemplate jdbcTemplate;

    private final static String MY_LEND_LIST_SQL = "select * from lend_list, book_info where reader_id = ? and book_info.book_id = lend_list.book_id";
    private final static String ALL_LEND_LIST_SQL = "select * from lend_list, book_info, reader_info where " +
            "book_info.book_id = lend_list.book_id and lend_list.reader_id=reader_info.reader_id and book_info.state = 0 and back_date is null";
    private final static String LEND_SQL = "update book_info set state = 0 where book_id = ?";
    private final static String ADD_LEND_LIST_SQL = "insert into lend_list (book_id, reader_id, lend_date, deadline) values (?, ?, ?, ?)";
    private final static String RETURN_BOOK_INFO_SQL = "update book_info set state = 1 where book_id = ?";
    private final static String RETURN_LEND_LIST_SQL =  "update lend_list set back_date = ? where book_id = ?";
    private final static String LEND_COUNT_SQL = "select count(*) from lend_list where reader_id = ? and back_date is null ";
    private final static String REFRESH_DEADLINE_SQL = "update lend_list set deadline = ? where sernum = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ArrayList<LendList> myLendList(String reader_id, final int card_state){
        final ArrayList<LendList> list=new ArrayList<LendList>();
        jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[]{reader_id},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    LendList lendList=new LendList();
                    lendList.setBack_date(resultSet.getDate("back_date"));
                    lendList.setBook_id(resultSet.getString("book_id"));
                    lendList.setLend_date(resultSet.getDate("lend_date"));
                    lendList.setReader_id(resultSet.getString("reader_id"));
                    lendList.setSernum(resultSet.getInt("sernum"));
                    lendList.setBook_name(resultSet.getString("name"));
                    lendList.setDeadline(resultSet.getString("deadline"));
                    list.add(lendList);
                }
            }
        });
        return list;
    }

    public void refreshDeadline (Date lend_date, int sernum, int card_state) {
        Calendar calendar = Calendar.getInstance();
//        System.out.println(lend_date);
        calendar.setTime(lend_date);
        if(card_state == 1) {
            calendar.add(Calendar.MONTH, 1);
        } else if (card_state == 2) {
            calendar.add(Calendar.MONTH, 2);
        } else {
            calendar.add(Calendar.MONTH, 3);
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(fmt.format(calendar.getTime()));
//        System.out.println(sernum);
        jdbcTemplate.update(REFRESH_DEADLINE_SQL, fmt.format(calendar.getTime()), sernum);
    }

    public ArrayList<LendList> adminLendList() {
        final ArrayList<LendList> lists = new ArrayList<LendList>();

        jdbcTemplate.query(ALL_LEND_LIST_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    LendList lendList = new LendList();
                    lendList.setBack_date(resultSet.getDate("back_date"));
                    lendList.setBook_id(resultSet.getString("book_id"));
                    lendList.setLend_date(resultSet.getDate("lend_date"));
                    lendList.setReader_id(resultSet.getString("reader_id"));
                    lendList.setSernum(resultSet.getInt("sernum"));
                    lendList.setBook_name(resultSet.getString("name"));
                    lendList.setDeadline(resultSet.getString("deadline"));
                    lists.add(lendList);
                }
            }
        });
        return lists;
    }

    public int getMaxCount(int card_state) {
        if (card_state == 1) {
            return 5;
        } else if (card_state == 2) {
            return 10;
        } else {
            return 20;
        }
    }

    public int lendBook(String book_id, String reader_id, int card_state) {
        if(lendCount(reader_id) <= (getMaxCount(card_state) - 1)) {
            Calendar calendar = Calendar.getInstance();
            if(card_state == 1) {
                calendar.add(Calendar.MONTH, 1);
            } else if (card_state == 2) {
                calendar.add(Calendar.MONTH, 2);
            } else {
                calendar.add(Calendar.MONTH, 3);
            }
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            jdbcTemplate.update(ADD_LEND_LIST_SQL, book_id, reader_id, new Date(), fmt.format(calendar.getTime()));
            return jdbcTemplate.update(LEND_SQL, book_id);
        } else {
            return -1; //-1: 借阅图书超过限量
        }
    }

    public int returnBook(String book_id) {
        System.out.println(book_id);
        jdbcTemplate.update(RETURN_BOOK_INFO_SQL, book_id);
        return  jdbcTemplate.update(RETURN_LEND_LIST_SQL, new Date(), book_id);
    }

    public int lendCount (String reader_id) {
        return jdbcTemplate.queryForObject(LEND_COUNT_SQL, new Object[] {reader_id}, Integer.class);
    }

}
