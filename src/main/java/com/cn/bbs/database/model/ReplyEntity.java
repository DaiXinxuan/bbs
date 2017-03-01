package com.cn.bbs.database.model;

import com.cn.bbs.param.ReplyParam;

/**
 * Created by dxx on 2017/2/22.
 */
public class ReplyEntity {
    private int replyId;
    private String replymanId;
    private int topicId;
    private String content;
    private String replyDate;
    private int quoteReplyId;
    private String quoteUserId;


    public ReplyEntity() {}

    public ReplyEntity(String replymanId, int topicId, String content, String replyDate, int quoteReplyId) {
        this.replymanId = replymanId;
        this.topicId = topicId;
        this.content = content;
        this.replyDate = replyDate;
        this.quoteReplyId = quoteReplyId;
    }

    public ReplyEntity(ReplyParam replyParam) {
        this.content = replyParam.getContent();
        this.replymanId = replyParam.getReplyUserId();
        this.topicId = replyParam.getTopicId();
        this.quoteReplyId = replyParam.getQuoteReplyId();
    }

    public ReplyEntity(String replymanId, int topicId, String content, String replyDate) {
        this.replymanId = replymanId;
        this.topicId = topicId;
        this.content = content;
        this.replyDate = replyDate;
    }


    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplymanId() {
        return replymanId;
    }

    public void setReplymanId(String replymanId) {
        this.replymanId = replymanId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public int getQuoteReplyId() {
        return quoteReplyId;
    }

    public void setQuoteReplyId(int quoteReplyId) {
        this.quoteReplyId = quoteReplyId;
    }

    public String getQuoteUserId() {
        return quoteUserId;
    }

    public void setQuoteUserId(String quoteUserId) {
        this.quoteUserId = quoteUserId;
    }
}
