package com.library.service;

import com.library.dao.ReaderCardDao;
import com.library.domain.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.util.ArrayList;

@Service
public class ReaderCardService {

    private ReaderCardDao readerCardDao;

    public boolean hasMatchUser(String reader_id, String pwd) {
        int matchCount = readerCardDao.getMatchCount(reader_id, pwd);
        return matchCount > 0;
    }

    public ReaderCard findReaderById(String reader_id) {
        return readerCardDao.findReaderById(reader_id);
    }

    public boolean rePassword(String reader_id, String new_pwd) {
        return readerCardDao.rePassword(reader_id, new_pwd) > 0;
    }

    public void deleteReaderCard(String reader_id) {
        readerCardDao.deleteReaderCard(reader_id);
    }

    public ArrayList<ReaderCard> showAllReaderCard () {
        return readerCardDao.showAllReaderCard();
    }

    public boolean addReaderCard(ReaderCard readerCard) {
        return readerCardDao.addReaderCard(readerCard) > 0;
    }

    public boolean hasMatchAccount(String reader_id) {
        return readerCardDao.hasMatchAccount(reader_id) <= 0;
    }

    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }
}
