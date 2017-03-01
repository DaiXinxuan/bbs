package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.StickyEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface StickyDao {
    int addNewSticky(StickyEntity stickyEntity);

    List<StickyEntity> getAllStick();

    int deleteSticky(int topicId);

    StickyEntity whetherSticky(int topicId);
}
