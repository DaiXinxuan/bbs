package com.cn.bbs.servie;

import com.cn.bbs.database.model.ReplyEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface ReplyService {
    int postNewReply(ReplyEntity replyEntity);

    ReplyEntity getReplyInfoById(int replyId);

    List<ReplyEntity> getUserReplys(String replymanId);

    List<ReplyEntity> getTopicReplys(int topicId);
}
