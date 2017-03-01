package com.cn.bbs.param;

/**
 * Created by dxx on 2017/2/24.
 */
public class LoginParam {
    private String userId;
    private String password;

    public LoginParam() {}

    public LoginParam(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
