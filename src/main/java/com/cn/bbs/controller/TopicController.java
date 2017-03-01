package com.cn.bbs.controller;

import com.cn.bbs.database.model.ReplyEntity;
import com.cn.bbs.database.model.StickyEntity;
import com.cn.bbs.database.model.TopicEntity;
import com.cn.bbs.database.model.UserEntity;
import com.cn.bbs.param.TopicParam;
import com.cn.bbs.result.GeneralTopicResult;
import com.cn.bbs.result.ReplyResult;
import com.cn.bbs.result.ResultJson;
import com.cn.bbs.result.SpecificTopicResult;
import com.cn.bbs.servie.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dxx on 2017/2/21.
 */
@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StickyService stickyService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    Log log = LogFactory.getLog(TopicController.class);

    /**
     * 用户发表一个帖子
     * @param topicParam
     * @return 返回该帖子的id
     */
    @RequestMapping(value = "/postNewTopic",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson postNewTopic(@RequestBody TopicParam topicParam) {
        Date date = new Date();
        ResultJson resultJson = new ResultJson(false);
        String userId = topicParam.getPostUserId();
        if (userService.getUserInfoById(userId) == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("错误的用户Id");
            return resultJson;
        }
        if (StringUtils.isEmpty(topicParam.getContent())||
                StringUtils.isEmpty(topicParam.getTitle())) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("不允许发表标题或者内容为空的帖子");
            return resultJson;
        }

        TopicEntity topicEntity = new TopicEntity(topicParam);
        //设置帖子发表时间
        topicEntity.setPostDate(date.getTime()/1000 + "");
        //设置回复数和浏览量为0
        topicEntity.setComments(0);
        topicEntity.setBrowseCount(0);

        topicEntity.setLastReplyTime("");
        topicEntity.setLastReplyId("");

        int result = topicService.postNewTopic(topicEntity);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库存储失败");
            return resultJson;
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(topicEntity.getTopicId());
        return resultJson;
    }

    /**
     * 查看帖子及其具体楼层信息
     * @param topicId
     * @return
     */
    @RequestMapping(value = "/getTopicInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getTopicInfo(@RequestParam int topicId,
                                   @RequestParam int index) {
        ResultJson resultJson = new ResultJson(false);
        if (index < 1) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("页数不在符合范围内");
            return resultJson;
        }
        TopicEntity topicEntity = topicService.getTopicById(topicId);
        if (null == topicEntity) {
            resultJson.setErrorCode(1002);
            resultJson.setErrorMsg("数据库无此贴信息，可能已被管理员删除");
            return resultJson;
        }

        String topicTitle = topicEntity.getTitle();
        int categoryId = topicEntity.getCategoryId();
        String topicContent = topicEntity.getContent();
        String postUserId = topicEntity.getPostmanId();
        UserEntity userEntity = userService.getUserInfoById(postUserId);
        String postUserName = userEntity.getNickName();
        String postUserUrl = userEntity.getIconUrl();
        String postDate = dateFormat.format(new Date
                (Long.parseLong(topicEntity.getPostDate())*1000));
        //获取主楼内容
        SpecificTopicResult specificTopicResult = new SpecificTopicResult(topicTitle, topicContent,postDate,postUserUrl,
                postUserName, postUserId);
        specificTopicResult.setCategory(categoryService.getCategoryById(categoryId).getName());
        ArrayList<ReplyResult> replyResults = new ArrayList<ReplyResult>();
        ArrayList<ReplyEntity> replyEntities = (ArrayList<ReplyEntity>)
                replyService.getTopicReplys(topicId);
        int size = 1;
        if (replyEntities != null) {
            size = replyEntities.size() + 1;
        }
        List<ReplyEntity> twRes;
        if (replyEntities!=null && replyEntities.size()>0) {
            if (index*20 < size) {
                if (index == 1) {
                    twRes = replyEntities.subList(0, 19);
                } else {
                    twRes = replyEntities.subList((index-1)*20-1, index*20-1);
                }
            } else if ((index-1)*20 < size) {
                if (index == 1) {
                    twRes = replyEntities.subList(0, size-1);
                } else {
                    twRes = replyEntities.subList((index - 1) * 20 - 1, size - 1);
                }
            } else {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("后台帖子数量不足以展开到这一页");
                return resultJson;
            }
            for (ReplyEntity replyEntity:twRes) {
                if (replyEntity != null) {
                    ReplyResult replyResult = new ReplyResult();
                    replyResult.setContent(replyEntity.getContent());
                    replyResult.setReplyUserId(replyEntity.getReplymanId());
                    String replyDate=dateFormat.format(new Date(Long.parseLong(replyEntity.getReplyDate())*1000));
                    replyResult.setReplyDate(replyDate);

                    UserEntity userEntity1 = userService.getUserInfoById(replyEntity.getReplymanId());
                    replyResult.setReplyUserName(userEntity1.getNickName());
                    replyResult.setReplyUserUrl(userEntity1.getIconUrl());
                    //将本条回复回复的回复的ID返回
                    int quoteId = replyEntity.getQuoteReplyId();
                    replyResult.setQuoteReplyId(quoteId);
                    //如果回复回复的是帖子中的具体楼层而不是主楼内容，则将具体楼层的用户Id返回
                    if (quoteId != 0) {
                        String quoteUsrId = replyService.getReplyInfoById(quoteId).getReplymanId();
                        replyResult.setQuoteReplyUserId(quoteUsrId);
                        replyResult.setQuoteReplyUserName(userService.getUserInfoById(quoteUsrId).getNickName());
                    }
                    replyResults.add(replyResult);
                }
            }
        }
        specificTopicResult.setReplyResults(replyResults);

        //同时增加帖子浏览量
        int browseCount = topicEntity.getBrowseCount() + 1;
        topicService.updateBrowseCount(topicId, browseCount);

        resultJson.setStatus(true);
        if (index == 1) {
            resultJson.setResultObj(specificTopicResult);
        } else {
            resultJson.setResultObj(replyResults);
        }
        return resultJson;
    }

    /**
     * 用户通过关键字搜索相关帖子
     * @param keyMaps
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson searchTopicByKeywords(@RequestBody Map<String, String> keyMaps) {
        ResultJson resultJson = new ResultJson(false);
        String keywords="";
        if (keyMaps == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("请输入关键字！");
            return resultJson;
        }
        keywords = keyMaps.get("keywords");
        if (StringUtils.isEmpty(keywords)) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("请输入关键字！");
            return resultJson;
        }
        ArrayList<TopicEntity> topicEntities = (ArrayList<TopicEntity>)
                topicService.searchTopicsByKeyWords(keywords);
        ArrayList<GeneralTopicResult> topicResults=new ArrayList<GeneralTopicResult>();
        if (topicEntities!=null && topicEntities.size()>0) {
            for (TopicEntity topicEntity:topicEntities) {
                GeneralTopicResult topicResult = new GeneralTopicResult(topicEntity);
                topicResult.setPostUserName(userService.
                        getUserInfoById(topicEntity.getPostmanId()).getNickName());
                topicResult.setCategoryName(categoryService.
                        getCategoryById(topicEntity.getCategoryId()).getName());
                topicResults.add(topicResult);
            }
        } else {
            resultJson.setErrorCode(1002);
            resultJson.setErrorMsg("未找到包含该关键字的帖子");
            return resultJson;
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(topicResults);
        return resultJson;
    }

    /**
     * 用户根据传入页码获取那一页的所有帖子
     * @param index
     * @return
     */
    @RequestMapping(value = "/getTopics",method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getTwentyTopics(@RequestParam int index) {
        ResultJson resultJson = new ResultJson(false);
        if (index < 1) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("传入页数不合法");
            return resultJson;
        }
        //获取置顶帖数量
        ArrayList<StickyEntity> stickyEntities = (ArrayList<StickyEntity>)
                stickyService.getAllStick();
        int stickSize = stickyEntities==null? 0: stickyEntities.size();
        ArrayList<TopicEntity> topicEntities = (ArrayList<TopicEntity>)
                topicService.getRecentReplyTopics();
        int notStickyTopicAmount = topicEntities==null?0:topicEntities.size();
        int topicAmount = notStickyTopicAmount + stickSize;
        HashMap<String, Object> result = new HashMap<String, Object>();
        //将置顶帖数量和帖子总数量加入返回值
        result.put("stickCount", stickSize);
        result.put("topicAmount", topicAmount);
        //帖子数量为0时直接返回
        if (topicAmount == 0) {
            resultJson.setStatus(true);
            resultJson.setResultObj(result);
            return resultJson;
        }
        List<TopicEntity> resultEntities;
        ArrayList<GeneralTopicResult> topicResults = new ArrayList<GeneralTopicResult>();
        //当有置顶帖时
        if (stickSize > 0) {
            for (StickyEntity stickyEntity : stickyEntities) {
                TopicEntity topicEntity = topicService.getTopicById(stickyEntity.getTopicId());
                GeneralTopicResult topicResult = new GeneralTopicResult(topicEntity);
                topicResult.setPostUserName(userService.
                        getUserInfoById(topicEntity.getPostmanId()).getNickName());
                topicResult.setCategoryName(categoryService.
                        getCategoryById(topicEntity.getCategoryId()).getName());
                topicResults.add(topicResult);
            }
        }
        if (topicEntities != null && topicEntities.size() > 0) {
            if (index * 20 < topicAmount) {
                if (index == 1) {
                    resultEntities = topicEntities.subList(0, 20-stickSize);
                } else {
                    resultEntities = topicEntities.subList(20*(index-1)-stickSize,
                            20*index-stickSize);
                }
            } else if ((index - 1) * 20 < topicAmount) {
                if (index == 1) {
                    resultEntities = topicEntities.subList(0, notStickyTopicAmount);
                } else {
                    resultEntities = topicEntities.subList(20*(index-1)-stickSize,
                            notStickyTopicAmount);
                }
            } else {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("后台帖子数量不足以展开到这一页");
                return resultJson;
            }
            for (TopicEntity topicEntity : resultEntities) {
                GeneralTopicResult topicResult = new GeneralTopicResult(topicEntity);
                topicResult.setPostUserName(userService.
                        getUserInfoById(topicEntity.getPostmanId()).getNickName());
                topicResult.setCategoryName(categoryService.
                        getCategoryById(topicEntity.getCategoryId()).getName());
                topicResults.add(topicResult);
            }
        }
        result.put("topics", topicResults);
        resultJson.setStatus(true);
        resultJson.setResultObj(result);
        return resultJson;
    }

    /**
     * 获取用户历史发帖记录
     * @param userId
     * @param index
     * @return
     */
    @RequestMapping(value = "/getHistoryTopics", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getUserTopics(@RequestParam String userId,
                                    @RequestParam int index) {
        ResultJson resultJson = new ResultJson(false);
        if (index < 1) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("传入页数不合法");
            return resultJson;
        }

        ArrayList<TopicEntity> topicEntities = (ArrayList<TopicEntity>)
                topicService.getTopicsByUserId(userId);
        List<TopicEntity> resultEntities;
        ArrayList<GeneralTopicResult> topicResults = new ArrayList<GeneralTopicResult>();
        if (topicEntities!=null && topicEntities.size()>0) {
            int amount = topicEntities.size();
            if (index * 20 < amount) {
                int from = (index - 1) * 20;
                int to = 20 * index;
                resultEntities = topicEntities.subList(from, to);
            } else if ((index - 1) * 20 < amount) {
                int from = (index - 1) * 20;
                resultEntities = topicEntities.subList(from, amount);
            } else {
                resultJson.setErrorCode(1003);
                resultJson.setErrorMsg("该用户所发帖子数量不足以展开到这一页");
                return resultJson;
            }
            for (TopicEntity topicEntity : resultEntities) {
                GeneralTopicResult topicResult = new GeneralTopicResult(topicEntity);
                topicResult.setPostUserName(userService.
                        getUserInfoById(topicEntity.getPostmanId()).getNickName());
                topicResult.setCategoryName(categoryService.
                        getCategoryById(topicEntity.getCategoryId()).getName());
                topicResults.add(topicResult);
            }
        }
        resultJson.setStatus(true);
        resultJson.setResultObj(topicResults);
        return resultJson;
    }

    /**
     * 用户获取属于某个分类的所有帖子
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/getCategoryTopics", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson getTopicsBelongToCategory(@RequestParam int categoryId) {
        ResultJson resultJson = new ResultJson(false);

        ArrayList<TopicEntity> topicEntities = (ArrayList<TopicEntity>)
                topicService.getTopicsBelongToCategory(categoryId);
        ArrayList<GeneralTopicResult> topicResults=new ArrayList<GeneralTopicResult>();
        if (topicEntities!=null && topicEntities.size()>0) {
            for (TopicEntity topicEntity:topicEntities) {
                GeneralTopicResult topicResult = new GeneralTopicResult(topicEntity);
                topicResult.setPostUserName(userService.
                        getUserInfoById(topicEntity.getPostmanId()).getNickName());
                topicResult.setCategoryName(categoryService.
                        getCategoryById(topicEntity.getCategoryId()).getName());
                topicResults.add(topicResult);
            }
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(topicResults);
        return resultJson;
    }
}
