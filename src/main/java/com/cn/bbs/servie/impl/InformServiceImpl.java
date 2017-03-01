package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.InformDao;
import com.cn.bbs.database.model.InformEntity;
import com.cn.bbs.servie.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class InformServiceImpl implements InformService{
    @Autowired
    InformDao informDao;

    public int generateNewInform(InformEntity informEntity) {
        return informDao.generateNewInform(informEntity);
    }

    public int updateInformReadStatus(int informId) {
        return informDao.updateInformReadStatus(informId);
    }

    public List<InformEntity> getInformsByUserId(String userId) {
        return informDao.getInformsByUserId(userId);
    }

    public int deleteInforms(String userId) {
        return informDao.deleteInforms(userId);
    }
}
