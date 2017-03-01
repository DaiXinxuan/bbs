package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.AnnounceDao;
import com.cn.bbs.database.model.AnnounceEntity;
import com.cn.bbs.servie.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    AnnounceDao announceDao;

    public int updateAnnounce(AnnounceEntity announceEntity) {
        return announceDao.updateAnnounce(announceEntity);
    }

    public int addAnnounce(AnnounceEntity announceEntity) {
        return announceDao.addAnnounce(announceEntity);
    }

    public AnnounceEntity getAnnounce() {
        return announceDao.getAnnounce();
    }
}
