package com.cn.bbs.database.model;

/**
 * Created by dxx on 2017/2/22.
 */
public class StickyEntity {
    private int topicId;
    private String stickTime;

    public StickyEntity() {}

    public StickyEntity(int topicId, String stickTime) {
        this.topicId = topicId;
        this.stickTime = stickTime;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getStickTime() {
        return stickTime;
    }

    public void setStickTime(String stickTime) {
        this.stickTime = stickTime;
    }
}
