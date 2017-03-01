package com.cn.bbs.result;

import com.cn.bbs.database.model.TopicEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dxx on 2017/2/24.
 */
public class GeneralTopicResult {
    private int topicId;
    private String postmanId;
    private String postDate;
    private String categoryName;
    private String title;
    private String content;
    private String lastReplyTime;
    private String lastReplyId;
    private int comments;
    private int browseCount;
    private String postUserName;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public GeneralTopicResult() {}

    public GeneralTopicResult(TopicEntity topicEntity) {
        this.topicId = topicEntity.getTopicId();
        this.postmanId = topicEntity.getPostmanId();
        this.postDate = dateFormat.format(new Date(Long.parseLong(topicEntity.getPostDate())*1000));
        this.title = topicEntity.getTitle();
        this.content = topicEntity.getContent();
        if (!topicEntity.getLastReplyTime().equals("")) {
            this.lastReplyTime = dateFormat.format(new Date(Long.parseLong(topicEntity.getLastReplyTime()) * 1000));
        }
        this.lastReplyId = topicEntity.getLastReplyId();
        this.comments = topicEntity.getComments();
        this.browseCount = topicEntity.getBrowseCount();
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

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
