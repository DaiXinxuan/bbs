package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.TopicDao;
import com.cn.bbs.database.model.TopicEntity;
import com.cn.bbs.servie.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicDao topicDao;


    public int postNewTopic(TopicEntity topicEntity) {
        return topicDao.postNewTopic(topicEntity);
    }

    public List<TopicEntity> getTopicsByUserId(String userId) {
        return topicDao.getTopicsByUserId(userId);
    }

    public List<TopicEntity> getRecentReplyTopics() {
        return topicDao.getRecentReplyTopics();
    }

    public TopicEntity getTopicById(int topicId) {
        return topicDao.getTopicById(topicId);
    }

    public List<TopicEntity> searchTopicsByKeyWords(String keywords) {
        return topicDao.searchTopicsByKeyWords(keywords);
    }

    public int deleteTopic(int topicId) {
        return topicDao.deleteTopic(topicId);
    }

    public int updateBrowseCount(int topicId,int browseCount) {
        return topicDao.updateBrowseCount(topicId, browseCount);
    }

    public int updateComments(int topicId, String lastReplyId, String lastReplyTime, int commentsCount) {
        return topicDao.updateComments(topicId,lastReplyId, lastReplyTime, commentsCount);
    }

    public List<TopicEntity> getTopicsBelongToCategory(int categoryId) {
        return topicDao.getTopicsBelongToCategory(categoryId);
    }
}
