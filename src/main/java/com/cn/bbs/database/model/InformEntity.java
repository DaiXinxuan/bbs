package com.cn.bbs.database.model;

/**
 * Created by dxx on 2017/2/22.
 */
public class InformEntity {
    private int informId;
    private String informUserId;
    private String content;
    private int replyId;
    private String informDate;
    private int hasRead;

    public InformEntity() {}

    public InformEntity(String informUserId, String content,int replyId,String informDate, int hasRead) {
        this.informUserId = informUserId;
        this.content = content;
        this.replyId = replyId;
        this.informDate = informDate;
        this.hasRead = hasRead;
    }

    public int getInformId() {
        return informId;
    }

    public void setInformId(int informId) {
        this.informId = informId;
    }

    public String getInformUserId() {
        return informUserId;
    }

    public void setInformUserId(String informUserId) {
        this.informUserId = informUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInformDate() {
        return informDate;
    }

    public void setInformDate(String informDate) {
        this.informDate = informDate;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }
}
