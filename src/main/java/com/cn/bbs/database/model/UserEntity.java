package com.cn.bbs.database.model;


/**
 * Created by dxx on 2017/2/24.
 */
public class UserEntity {
    private String userId;
    private String nickName;
    private String iconUrl;

    public UserEntity() {}

    public UserEntity(String userId, String nickName, String iconUrl) {
        this.userId = userId;
        this.nickName = nickName;
        this.iconUrl = iconUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
