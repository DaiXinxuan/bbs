package com.cn.bbs.database.model;

import com.cn.bbs.param.TopicParam;

/**
 * Created by dxx on 2017/2/21.
 */
public class TopicEntity {
    private int topicId;
    private String postmanId;
    private String postDate;
    private int categoryId;
    private String title;
    private String content;
    private String lastReplyTime;
    private String lastReplyId;
    private int comments;
    private int browseCount;

    public TopicEntity() {}

    public TopicEntity(String postmanId, String postDate, int categoryId, String title, String content) {
        this.postmanId = postmanId;
        this.postDate = postDate;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public TopicEntity(TopicParam topicParam) {
        this.categoryId = topicParam.getCategoryId();
        this.postmanId = topicParam.getPostUserId();
        this.title = topicParam.getTitle();
        this.content = topicParam.getContent();
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(String postmanId) {
        this.postmanId = postmanId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(String lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(String lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }
}
