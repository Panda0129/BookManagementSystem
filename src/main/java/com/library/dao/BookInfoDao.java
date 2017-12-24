package com.library.dao;

import com.library.domain.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class BookInfoDao {
    private JdbcTemplate jdbcTemplate;
    private final static String SHOW_ALL_BOOK_INFO_SQL = "select * from book_info";
    private final static String SHOW_BOOK_INFO_BY_ID_SQL = "select * from book_info where book_id = ?";
    private final static String MATCH_BOOK_SQL = "select count(*) from book_info where book_id like ? or name like ? or ISBN like ?";
    private final static String QUERY_BOOK_SQL = "select * from book_info where book_id like ? or name like ? or ISBN like ?";
    private final static String ADD_NEW_BOOK_SQL = "insert into book_info (book_id, name, author, publish, ISBN, language, price, " +
            " pubdate, class_id, pressmark, link) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String GET_BOOK_CLASS_SQL = "select * from class_info";
    private final static String GET_BOOK_COUNT_VIA_ID = "select count(*) from book_info where book_id = ?";
    private final static String REPORT_BOOK_LOSS = "update book_info set state = -1 where book_id = ?";
    private final static String DELETE_BOOK_SQL = "delete from book_info where book_id = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteBook (String book_id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, book_id);
    }

    public void reportBookLoss(String book_id) {
        jdbcTemplate.update(REPORT_BOOK_LOSS, book_id);
    }

    public ArrayList<BookInfo> showAllBookInfo() {
        final ArrayList<BookInfo> bookInfos = new ArrayList<BookInfo>();
        jdbcTemplate.query(SHOW_ALL_BOOK_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while(resultSet.next()) {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setBook_id(resultSet.getString("book_id"));
                    bookInfo.setName(resultSet.getString("name"));
                    bookInfo.setAuthor(resultSet.getString("author"));
                    bookInfo.setPublish(resultSet.getString("publish"));
                    bookInfo.setISBN(resultSet.getString("ISBN"));
                    bookInfo.setLanguage(resultSet.getString("language"));
                    bookInfo.setPrice(resultSet.getBigDecimal("price"));
                    bookInfo.setPubdate(resultSet.getDate("pubdate"));
                    bookInfo.setClass_id(resultSet.getInt("class_id"));
                    bookInfo.setPressmark(resultSet.getInt("pressmark"));
                    bookInfo.setState(resultSet.getInt("state"));
                    bookInfos.add(bookInfo);
                }
            }
        });
        return bookInfos;
    }

    public BookInfo showBookInfoById (String book_id) {

        final BookInfo info = new BookInfo();
        jdbcTemplate.query(SHOW_BOOK_INFO_BY_ID_SQL, new Object[]{book_id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                info.setName(resultSet.getString("name"));
                info.setAuthor(resultSet.getString("author"));
                info.setPublish(resultSet.getString("publish"));
                info.setPrice(resultSet.getBigDecimal("price"));
                info.setPubdate(resultSet.getDate("pubdate"));
                info.setLink(resultSet.getString("link"));
                info.setState(resultSet.getInt("state"));
                info.setBook_id(resultSet.getString("book_id"));
            }
        });
        return info;
    }

    public int getRepeatBookById (String book_id) {
        return jdbcTemplate.queryForObject(GET_BOOK_COUNT_VIA_ID, new Object[] {book_id}, Integer.class);
    }

    public int matchBook (String input) {
        String search = "%" + input + "%";
        System.out.println(search);
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL, new Object[] {search, search, search}, Integer.class);
    }

    public ArrayList<BookInfo> queryBook(String input) {
        String search = "%" + input + "%";
        final ArrayList<BookInfo> bookInfos = new ArrayList<BookInfo>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{search, search, search}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setBook_id(resultSet.getString("book_id"));
                    bookInfo.setName(resultSet.getString("name"));
                    bookInfo.setAuthor(resultSet.getString("author"));
                    bookInfo.setPublish(resultSet.getString("publish"));
                    bookInfo.setPrice(resultSet.getBigDecimal("price"));
                    bookInfo.setPubdate(resultSet.getDate("pubdate"));
                    bookInfo.setLink(resultSet.getString("link"));
                    bookInfo.setState(resultSet.getInt("state"));
                    bookInfos.add(bookInfo);
                }
            }
        });

        return bookInfos;
    }

    public int addNewBook(BookInfo bookInfo) {
        return jdbcTemplate.update(ADD_NEW_BOOK_SQL, new Object[] {bookInfo.getBook_id(), bookInfo.getName(), bookInfo.getAuthor(),
                                bookInfo.getPublish(), bookInfo.getISBN(), bookInfo.getLanguage(), bookInfo.getPrice(),
                                bookInfo.getPubdate(), bookInfo.getClass_id(), bookInfo.getPressmark(), bookInfo.getLink()});
    }

    public HashMap<Integer, String> getBookClass() {
        final HashMap<Integer, String> bookClass = new HashMap<Integer, String>();
        jdbcTemplate.query(GET_BOOK_CLASS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    bookClass.put(resultSet.getInt("class_id"), resultSet.getString("class_name"));
                }
            }
        });

        return bookClass;
    }
}
