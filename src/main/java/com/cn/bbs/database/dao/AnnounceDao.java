package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.AnnounceEntity;

/**
 * Created by dxx on 2017/2/22.
 */
public interface AnnounceDao {
    int updateAnnounce(AnnounceEntity announceEntity);

    int addAnnounce(AnnounceEntity announceEntity);

    AnnounceEntity getAnnounce();
}
