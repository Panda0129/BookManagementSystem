package com.library.service;

import com.library.dao.LendDao;
import com.library.domain.LendList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

@Service
public class LendService {

    private LendDao lendDao;

    @Autowired
    public void setLendDao (LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public ArrayList<LendList> myLendList (String reader_id, int card_state) {
        return lendDao.myLendList(reader_id, card_state);
    }

    public ArrayList<LendList> adminLendList () {
        return lendDao.adminLendList();
    }

    public void refreshDeadline(Date lend_date, int sernum, int card_state) {
        lendDao.refreshDeadline(lend_date, sernum, card_state);
    }

    public int lendCount(String reader_id) {
        return lendDao.lendCount(reader_id);
    }

    public boolean lendBook(String book_id, String reader_id, int card_state) {
        return lendDao.lendBook(book_id, reader_id, card_state) > 0;
    }

    public boolean returnBook(String book_id) {
        return lendDao.returnBook(book_id) > 0;
    }
}
