package com.cn.bbs.controller;

import com.cn.bbs.database.model.InformEntity;
import com.cn.bbs.database.model.ReplyEntity;
import com.cn.bbs.database.model.TopicEntity;
import com.cn.bbs.database.model.UserEntity;
import com.cn.bbs.param.ReplyParam;
import com.cn.bbs.result.HistoryReplyResult;
import com.cn.bbs.result.ReplyResult;
import com.cn.bbs.result.ResultJson;
import com.cn.bbs.servie.InformService;
import com.cn.bbs.servie.ReplyService;
import com.cn.bbs.servie.TopicService;
import com.cn.bbs.servie.UserService;
import com.cn.bbs.util.InformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dxx on 2017/2/23.
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private InformService informService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    /**
     * 用户发表一条回复
     * @param replyParam
     * @return 返回该回复的id
     */
    @RequestMapping(value = "/postNewReply", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson postNewReply(@RequestBody ReplyParam replyParam) {
        Date date = new Date();
        ResultJson resultJson = new ResultJson(false);

        int topicId = replyParam.getTopicId();
        TopicEntity topic = topicService.getTopicById(topicId);
        if (topic == null) {
            resultJson.setErrorCode(1002);
            resultJson.setErrorMsg("数据库无此贴信息，可能已被管理员删除");
            return resultJson;
        }

        String replyUserId = replyParam.getReplyUserId();
        UserEntity userEntity = userService.getUserInfoById(replyUserId);
        if (userEntity == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("错误的用户Id");
            return resultJson;
        }
        if (StringUtils.isEmpty(replyParam.getContent())) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("不允许发表内容为空的回复");
            return resultJson;
        }
        if (replyParam.getQuoteReplyId()!=0) {
            ReplyEntity quoteReply = replyService.getReplyInfoById(replyParam.getQuoteReplyId());
            if (quoteReply.getTopicId()!=replyParam.getTopicId()) {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("引用回复的id与帖子id不符，请查证");
                return resultJson;
            }
            if (!quoteReply.getReplymanId().equals(replyParam.getQuoteUserId())) {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("引用回复的id与引用回复用户的id不符，请查证");
                return resultJson;
            }
        }

        ReplyEntity replyEntity = new ReplyEntity(replyParam);
        replyEntity.setReplyDate(date.getTime()/1000 + "");
        //存储这条回复
        int result = replyService.postNewReply(replyEntity);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库存储出错");
            return resultJson;
        }

        //增加帖子回复数,更新回复情况
        int commentsCount = topic.getComments() + 1;
        topicService.updateComments(topicId, replyParam.getReplyUserId(),
                date.getTime()/1000 + "", commentsCount);

        //同时生成通知
        String replyUserNickName = userEntity.getNickName();
        String informContent = "";
        String informUserId = "";
        String topicName = topic.getTitle();
        //判断是否是引用回复
        if (replyParam.getQuoteReplyId() != 0) {
            informContent = InformUtil.generateReplyInform(replyUserNickName, topicName);
            informUserId = replyService.getReplyInfoById(replyParam.getQuoteReplyId())
                    .getReplymanId();
        } else {
            informContent = InformUtil.generateTopicInform(replyUserNickName, topicName);
            informUserId = topicService.getTopicById(topicId).getPostmanId();
        }
        //不是本人回复自己的帖子或回复时才生成通知
        if (!informUserId.equals(replyUserId)) {
            InformEntity informEntity = new InformEntity(informUserId,
                    informContent, replyEntity.getReplyId(),date.getTime() / 1000 + "", 0);
            informService.generateNewInform(informEntity);
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(replyEntity.getReplyId());
        return resultJson;
    }

    /**
     * 用户点击未读通知或者历史回复获取回复信息
     * @param replyId
     * @return
     */
    @RequestMapping(value = "/getReplyInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getReplyInfoById(@RequestParam int replyId) {
        ResultJson resultJson = new ResultJson(false);
        ReplyEntity replyEntity = replyService.getReplyInfoById(replyId);
        if (replyEntity == null) {
            resultJson.setErrorCode(1002);
            resultJson.setErrorMsg("回复ID有误，未查询到该条回复");
            return resultJson;
        }

        String replyUserId = replyEntity.getReplymanId();
        UserEntity userEntity = userService.getUserInfoById(replyUserId);
        String replyUserName = userEntity.getNickName();
        String replyUserUrl = userEntity.getIconUrl();
        ReplyResult replyResult = new ReplyResult(replyUserId, replyUserName, replyEntity.getContent());
        replyResult.setReplyUserUrl(replyUserUrl);
        int quoteReplyId = replyEntity.getQuoteReplyId();
        if (quoteReplyId != 0) {
            String quoteUserId = replyEntity.getQuoteUserId();
            replyResult.setQuoteReplyUserId(quoteUserId);
            UserEntity quoteUserEntity = userService.getUserInfoById(quoteUserId);
            replyResult.setQuoteReplyUserName(quoteUserEntity.getNickName());
        }
        String date = dateFormat.format(new Date(Long.parseLong(replyEntity.getReplyDate())*1000));
        replyResult.setReplyDate(date);

        resultJson.setStatus(true);
        resultJson.setResultObj(replyResult);
        return resultJson;
    }

    /**
     * 用户查看历史回复记录
     * @param userId
     * @param index
     * @return
     */
    @RequestMapping(value = "/getHistoryReplies",method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getHistoryReply(@RequestParam String userId,
                                      @RequestParam int index) {
        ResultJson resultJson = new ResultJson(false);
        if (index < 1) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("传入页数不合法");
            return resultJson;
        }
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        ArrayList<ReplyEntity> replyEntities = (ArrayList<ReplyEntity>)
                replyService.getUserReplys(userId);
        List<ReplyEntity> replyEntityList;
        ArrayList<HistoryReplyResult> historyReplyResults = new ArrayList<HistoryReplyResult>();
        if (replyEntities!=null && replyEntities.size()>0) {
            int amount = replyEntities.size();
            resultMap.put("amount", amount);
            if (index*20 < amount) {
                int from = (index-1)*20;
                int to = 20*index;
                replyEntityList = replyEntities.subList(from, to);
            } else if ((index-1)*20 < amount){
                int from = (index-1)*20;
                replyEntityList = replyEntities.subList(from, amount);
            } else {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("回复数量不足以展开到这一页");
                return resultJson;
            }
            for (ReplyEntity replyEntity: replyEntityList) {
                HistoryReplyResult historyReplyResult;
                int topicId = replyEntity.getTopicId();
                TopicEntity topicEntity = topicService.getTopicById(topicId);
                String lastReplyManId = topicEntity.getLastReplyId();
                if (topicEntity == null) {
                    historyReplyResult = new HistoryReplyResult();
                    historyReplyResult.setTopicTitle("账号权限不足");
                } else {
                    historyReplyResult = new HistoryReplyResult(topicEntity);
                    historyReplyResult.setLastReplyUserName(
                            userService.getUserInfoById(lastReplyManId).getNickName());
                    historyReplyResult.setContent(replyEntity.getContent());
                    historyReplyResult.setReplyId(replyEntity.getReplyId());
                }
                historyReplyResults.add(historyReplyResult);
            }
            resultMap.put("replyList",historyReplyResults);
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(historyReplyResults);
        return resultJson;
    }
}
