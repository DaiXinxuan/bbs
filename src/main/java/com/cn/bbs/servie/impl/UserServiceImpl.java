package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.UserDao;
import com.cn.bbs.database.model.UserEntity;
import com.cn.bbs.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dxx on 2017/2/24.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public int addNewUser(UserEntity userEntity) {
        return userDao.addNewUser(userEntity);
    }

    public int updateUserIcon(String iconUrl, String userId) {
        return userDao.updateUserIcon(iconUrl, userId);
    }

    public int updateUserInfo(UserEntity userEntity) {
        return userDao.updateUserInfo(userEntity);
    }

    public UserEntity getUserInfoById(String userId) {
        return userDao.getUserInfoById(userId);
    }
}
