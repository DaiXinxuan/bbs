package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.InformEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface InformDao {
    int generateNewInform(InformEntity informEntity);

    int updateInformReadStatus(int informId);

    List<InformEntity> getInformsByUserId(String userId);

    int deleteInforms(String userId);
}
