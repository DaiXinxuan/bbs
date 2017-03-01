package com.cn.bbs.database.model;

import com.cn.bbs.param.AnnounceParam;

/**
 * Created by dxx on 2017/2/22.
 */
public class AnnounceEntity {
    private int announceId;
    private String title;
    private String content;
    private String announceTime;

    public AnnounceEntity() {}

    public AnnounceEntity(String title, String content, String announceTime) {
        this.title = title;
        this.content = content;
        this.announceTime = announceTime;
    }

    public AnnounceEntity(AnnounceParam announceParam) {
        this.title = announceParam.getTitle();
        this.content = announceParam.getContent();
    }

    public int getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(int announceId) {
        this.announceId = announceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(String announceTime) {
        this.announceTime = announceTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
