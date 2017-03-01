package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.AdminDao;
import com.cn.bbs.database.model.AdminEntity;
import com.cn.bbs.servie.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    public AdminEntity verifyAdmin(AdminEntity adminEntity) {
        return adminDao.verifyAdmin(adminEntity);
    }

    public int changePassword(String adminName, String newPassword) {
        return adminDao.changePassword(adminName, newPassword);
    }
}
