package com.cn.bbs.result;

import com.cn.bbs.database.model.ReplyEntity;
import com.cn.bbs.database.model.TopicEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dxx on 2017/2/27.
 */
public class HistoryReplyResult {
    private int replyId;
    private String content;
    private int topicId;
    private int category;
    private String topicTitle;
    private String lastReplyManId;
    private String lastReplyUserName;
    private String lastReplyTime;
    private int comments;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public HistoryReplyResult() {}

    public HistoryReplyResult(TopicEntity topicEntity) {
        this.category = topicEntity.getCategoryId();
        this.comments = topicEntity.getComments();
        this.topicTitle = topicEntity.getTitle();
        this.topicId = topicEntity.getTopicId();
        this.lastReplyManId = topicEntity.getLastReplyId();
        this.lastReplyTime = dateFormat.format(
                new Date(Long.parseLong(topicEntity.getLastReplyTime())*1000));
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getLastReplyManId() {
        return lastReplyManId;
    }

    public void setLastReplyManId(String lastReplyManId) {
        this.lastReplyManId = lastReplyManId;
    }

    public String getLastReplyUserName() {
        return lastReplyUserName;
    }

    public void setLastReplyUserName(String lastReplyUserName) {
        this.lastReplyUserName = lastReplyUserName;
    }

    public String getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(String lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
