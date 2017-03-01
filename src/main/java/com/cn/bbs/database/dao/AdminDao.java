package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.AdminEntity;

/**
 * Created by dxx on 2017/2/22.
 */
public interface AdminDao {
    AdminEntity verifyAdmin(AdminEntity adminEntity);

    int changePassword(String adminName, String newPassword);
}
