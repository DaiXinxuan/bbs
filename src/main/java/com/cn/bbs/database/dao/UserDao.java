package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.UserEntity;

/**
 * Created by dxx on 2017/2/24.
 */
public interface UserDao {
    int addNewUser(UserEntity userEntity);

    int updateUserIcon(String iconUrl,String userId);

    int updateUserInfo(UserEntity userEntity);

    UserEntity getUserInfoById(String userId);
}
