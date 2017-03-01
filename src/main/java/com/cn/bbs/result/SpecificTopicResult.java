package com.cn.bbs.result;

import java.util.ArrayList;

/**
 * Created by dxx on 2017/2/23.
 */
public class SpecificTopicResult {
    private String topicTitle;
    private String topicContent;
    private String postDate;
    private String postUserIconUrl;
    private String postUserNickName;
    private String postUserId;
    private ArrayList<ReplyResult> replyResults;
    private String category;

    public SpecificTopicResult() {}

    public SpecificTopicResult(String topicTitle, String topicContent, String postDate,String postUserIconUrl, String postUserNickName, String postUserId) {
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.postDate = postDate;
        this.postUserIconUrl = postUserIconUrl;
        this.postUserNickName = postUserNickName;
        this.postUserId = postUserId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getPostUserNickName() {
        return postUserNickName;
    }

    public void setPostUserNickName(String postUserNickName) {
        this.postUserNickName = postUserNickName;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }

    public ArrayList<ReplyResult> getReplyResults() {
        return replyResults;
    }

    public void setReplyResults(ArrayList<ReplyResult> replyResults) {
        this.replyResults = replyResults;
    }

    public String getPostUserIconUrl() {
        return postUserIconUrl;
    }

    public void setPostUserIconUrl(String postUserIconUrl) {
        this.postUserIconUrl = postUserIconUrl;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
