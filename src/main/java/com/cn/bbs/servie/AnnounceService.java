package com.cn.bbs.servie;

import com.cn.bbs.database.model.AnnounceEntity;

/**
 * Created by dxx on 2017/2/22.
 */
public interface AnnounceService {
    int updateAnnounce(AnnounceEntity announceEntity);

    int addAnnounce(AnnounceEntity announceEntity);

    AnnounceEntity getAnnounce();
}
