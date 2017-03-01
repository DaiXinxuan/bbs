package com.cn.bbs.param;

/**
 * Created by dxx on 2017/2/23.
 */
public class ReplyParam {
    private String replyUserId;
    private int topicId;
    private String content;
    private int quoteReplyId;
    private String quoteUserId;

    public ReplyParam() {}

    public ReplyParam(String replyUserId, int topicId, String content, int quoteReplyId, String quoteUserId) {
        this.replyUserId = replyUserId;
        this.topicId = topicId;
        this.content = content;
        this.quoteReplyId = quoteReplyId;
        this.quoteUserId = quoteUserId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
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
