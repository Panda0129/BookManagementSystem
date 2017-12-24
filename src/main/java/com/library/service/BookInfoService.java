package com.library.service;

import com.library.dao.BookInfoDao;
import com.library.domain.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class BookInfoService {

    private BookInfoDao bookInfoDao;

    public void deleteBook (String book_id ) {
        bookInfoDao.deleteBook(book_id);
    }

    public ArrayList<BookInfo> showAllBookInfo() {
        return bookInfoDao.showAllBookInfo();
    }

    public BookInfo showBookInfo(String book_id) {
        return bookInfoDao.showBookInfoById(book_id);
    }

    public void reportBookLoss(String book_id) {
        bookInfoDao.reportBookLoss(book_id);
    }

    public int hasRepeatBook (String book_id) {
        return bookInfoDao.getRepeatBookById(book_id);
    }

    public boolean hasMatchBook(String input) {
        return bookInfoDao.matchBook(input) > 0;
    }

    public ArrayList<BookInfo> queryBook (String input) {
        return bookInfoDao.queryBook(input);
    }

    public boolean addNewBook(BookInfo bookInfo) {
        return bookInfoDao.addNewBook(bookInfo) > 0;
    }

    public HashMap<Integer, String> getBookClass() {
        return bookInfoDao.getBookClass();
    }

    @Autowired
    public void setBookInfoDao(BookInfoDao bookInfoDao) {
        this.bookInfoDao = bookInfoDao;
    }
}
