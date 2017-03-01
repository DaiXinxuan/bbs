package com.cn.bbs.database.model;

/**
 * Created by dxx on 2017/2/22.
 */
public class AdminEntity {
    private String adminName;
    private String password;

    public AdminEntity() {}

    public AdminEntity(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
