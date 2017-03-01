package com.cn.bbs.param;

/**
 * Created by dxx on 2017/2/28.
 */
public class AnnounceParam {
    private String title;
    private String content;

    public AnnounceParam() {}

    public AnnounceParam(String title, String content) {
        this.title = title;
        this.content = content;
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
