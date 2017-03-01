package com.cn.bbs.param;

/**
 * Created by dxx on 2017/2/23.
 */
public class TopicParam {
    private String postUserId;
    private int categoryId;
    private String title;
    private String content;

    public TopicParam() {}

    public TopicParam(String postUserId, int categoryId, String title, String content) {
        this.postUserId = postUserId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
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
}
