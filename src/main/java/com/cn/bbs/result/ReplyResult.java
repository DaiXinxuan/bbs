package com.cn.bbs.result;

/**
 * Created by dxx on 2017/2/23.
 */
public class ReplyResult {
    private String replyUserId;
    private String replyUserName;
    private String replyUserUrl;
    private String replyDate;
    private String content;
    private int quoteReplyId;
    private String quoteReplyUserId;
    private String quoteReplyUserName;

    public ReplyResult() {}

    public ReplyResult(String replyUserId, String replyUserName, String content) {
        this.replyUserId = replyUserId;
        this.replyUserName = replyUserName;
        this.content = content;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuoteReplyId() {
        return quoteReplyId;
    }

    public void setQuoteReplyId(int quoteReplyId) {
        this.quoteReplyId = quoteReplyId;
    }

    public String getQuoteReplyUserId() {
        return quoteReplyUserId;
    }

    public void setQuoteReplyUserId(String quoteReplyUserId) {
        this.quoteReplyUserId = quoteReplyUserId;
    }

    public String getQuoteReplyUserName() {
        return quoteReplyUserName;
    }

    public void setQuoteReplyUserName(String quoteReplyUserName) {
        this.quoteReplyUserName = quoteReplyUserName;
    }

    public String getReplyUserUrl() {
        return replyUserUrl;
    }

    public void setReplyUserUrl(String replyUserUrl) {
        this.replyUserUrl = replyUserUrl;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }
}
