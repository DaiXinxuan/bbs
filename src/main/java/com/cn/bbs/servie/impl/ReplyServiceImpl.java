package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.ReplyDao;
import com.cn.bbs.database.model.ReplyEntity;
import com.cn.bbs.servie.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyDao replyDao;

    public int postNewReply(ReplyEntity replyEntity) {
        return replyDao.postNewReply(replyEntity);
    }

    public ReplyEntity getReplyInfoById(int replyId) {
        return replyDao.getReplyInfoById(replyId);
    }

    public List<ReplyEntity> getUserReplys(String replymanId) {
        return replyDao.getUserReplys(replymanId);
    }

    public List<ReplyEntity> getTopicReplys(int topicId) {
        return replyDao.getTopicReplys(topicId);
    }
}
