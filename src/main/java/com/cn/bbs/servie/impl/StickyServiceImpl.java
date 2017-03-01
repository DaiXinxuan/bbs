package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.StickyDao;
import com.cn.bbs.database.model.StickyEntity;
import com.cn.bbs.servie.StickyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class StickyServiceImpl implements StickyService {
    @Autowired
    StickyDao stickyDao;

    public int addNewSticky(StickyEntity stickyEntity) {
        return stickyDao.addNewSticky(stickyEntity);
    }

    public List<StickyEntity> getAllStick() {
        return stickyDao.getAllStick();
    }

    public int deleteSticky(int topicId) {
        return stickyDao.deleteSticky(topicId);
    }

    public StickyEntity whetherSticky(int topicId) {
        return stickyDao.whetherSticky(topicId);
    }
}
