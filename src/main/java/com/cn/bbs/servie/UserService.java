package com.cn.bbs.servie;

import com.cn.bbs.database.model.UserEntity;

/**
 * Created by dxx on 2017/2/24.
 */
public interface UserService {
    int addNewUser(UserEntity userEntity);

    int updateUserIcon(String iconUrl,String userId);

    int updateUserInfo(UserEntity userEntity);

    UserEntity getUserInfoById(String userId);
}
