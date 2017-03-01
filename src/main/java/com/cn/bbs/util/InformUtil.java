package com.cn.bbs.util;

/**
 * Created by dxx on 2017/2/23.
 */
public class InformUtil {

    public static String generateReplyInform(String replyUserName, String topicName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(replyUserName);
        stringBuilder.append(" ");
        stringBuilder.append("回复了你的回复");
        stringBuilder.append(" ");
        stringBuilder.append(topicName);
        return stringBuilder.toString();
    }

    public static String generateTopicInform(String replyUserName, String topicName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(replyUserName);
        stringBuilder.append(" ");
        stringBuilder.append("回复了你的帖子");
        stringBuilder.append(" ");
        stringBuilder.append(topicName);
        return stringBuilder.toString();
    }
}
