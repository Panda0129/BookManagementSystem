package com.library.service;

import com.library.dao.ReaderInfoDao;
import com.library.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderInfoService {
    private ReaderInfoDao readerInfoDao;

    @Autowired
    public void setReaderInfoDao(ReaderInfoDao readerInfoDao) {
        this.readerInfoDao = readerInfoDao;
    }

    public ReaderInfo showReaderInfo(String reader_id) {
        return  readerInfoDao.showReaderInfo(reader_id);
    }

    public boolean modifyIndividualInfo(ReaderInfo readerInfo) {
        return readerInfoDao.modifyIndividualInfo(readerInfo) > 0;
    }

    public boolean matchReader(String input) {
        return readerInfoDao.matchReader(input) > 0;
    }

    public ArrayList<ReaderInfo> queryReader (String input) {
        return readerInfoDao.queryReader(input);
    }

    public ArrayList<ReaderInfo> showAllReaderInfo() {
        return readerInfoDao.showAllReaderInfo();
    }
}
