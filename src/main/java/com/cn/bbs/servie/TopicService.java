package com.cn.bbs.servie;

import com.cn.bbs.database.model.TopicEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface TopicService {
    int postNewTopic(TopicEntity topicEntity);

    List<TopicEntity> getTopicsByUserId(String userId);

//    List<TopicEntity> getNewestTwentyTopics();

    List<TopicEntity> getRecentReplyTopics();

    TopicEntity getTopicById(int topicId);

    List<TopicEntity> searchTopicsByKeyWords(String keywords);

    int deleteTopic(int topicId);

    int updateBrowseCount(int topicId, int browseCount);

    int updateComments(int topicId, String lastReplyId, String lastReplyTime, int commentsCount);

    List<TopicEntity> getTopicsBelongToCategory(int categoryId);
}
