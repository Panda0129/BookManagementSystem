package com.library.service;

import com.library.dao.AdminDao;
import com.library.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminDao adminDao;

    @Autowired
    public void setAdminDao (AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public boolean getMatchAccount(String admin_id, String admin_pwd) {
        return adminDao.getMatchCount(admin_id, admin_pwd) > 0;
    }

    public Admin findAdminById (String admin_id) {
        return adminDao.findAdminById(admin_id);
    }
}
