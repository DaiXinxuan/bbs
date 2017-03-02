package com.cn.bbs.controller;

import com.cn.bbs.database.model.AdminEntity;
import com.cn.bbs.database.model.AnnounceEntity;
import com.cn.bbs.database.model.CategoryEntity;
import com.cn.bbs.database.model.StickyEntity;
import com.cn.bbs.param.AdminLoginParam;
import com.cn.bbs.param.AnnounceParam;
import com.cn.bbs.param.CategoryParam;
import com.cn.bbs.param.PasswordParam;
import com.cn.bbs.result.ResultJson;
import com.cn.bbs.servie.*;
import com.cn.bbs.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by dxx on 2017/2/27.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnnounceService announceService;
    @Autowired
    private StickyService stickyService;
    @Autowired
    private TopicService topicService;

    /**
     * 管理员登录系统
     * @param adminLoginParam
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson adminLogin(@RequestBody AdminLoginParam adminLoginParam,
                                 HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        String adminName = adminLoginParam.getAdminName();
        String truePassword;
        try {
            truePassword = MD5Util.md5Encode(adminLoginParam.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setErrorCode(1007);
            resultJson.setErrorMsg("MD5加密出错，请稍后再试");
            return resultJson;
        }
        AdminEntity adminEntity = new AdminEntity(adminName, truePassword);
        if (adminService.verifyAdmin(adminEntity)==null) {
            resultJson.setErrorCode(1005);
            resultJson.setErrorMsg("身份验证错误");
            return resultJson;
        }

        request.getSession().setAttribute("admin",adminName);
        request.getSession().setAttribute("isAdmin", true);
        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员更改密码
     * @param passwordParam
     * @param request
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson changePassword(@RequestBody PasswordParam passwordParam,
                                     HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }
        String admin = (String) request.getSession().getAttribute("admin");
        String prepass = passwordParam.getPrePassword();
        String newpass = passwordParam.getNewPassword();
        String MD5pre;
        String MD5new;
        try {
            MD5pre = MD5Util.md5Encode(prepass);
            MD5new = MD5Util.md5Encode(newpass);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setErrorCode(1007);
            resultJson.setErrorMsg("MD5加密出错，请稍后再试");
            return resultJson;
        }
        AdminEntity adminEntity = new AdminEntity(admin, MD5pre);
        if (adminService.verifyAdmin(adminEntity) == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("密码错误！");
            return resultJson;
        }
        int result = adminService.changePassword(admin, MD5new);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员注销登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson logout(HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(true);
        request.getSession().removeAttribute("isAdmin");
        request.getSession().invalidate();
        return resultJson;
    }

    /**
     * 管理员删除违规帖子
     * @param topicId
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteTopic",method = RequestMethod.GET)
    @ResponseBody
    public ResultJson deleteTopic(@RequestParam int topicId,
                                  HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }
        if (topicService.getTopicById(topicId) == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("帖子Id有误");
            return resultJson;
        }
        int result = topicService.deleteTopic(topicId);
        //若该帖子还是置顶帖，将置顶记录一起删除
        if (stickyService.whetherSticky(topicId)!=null) {
            stickyService.deleteSticky(topicId);
        }
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员设置置顶帖
     * @param topicId
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSticky", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson setStickTopics(@RequestParam int topicId,
                                     HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }
        if (topicService.getTopicById(topicId) == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("帖子Id有误，可能已被删除");
            return resultJson;
        }
        int count = stickyService.getAllStick().size();
        if (count >= 3) {
            resultJson.setErrorCode(1009);
            resultJson.setErrorMsg("置顶帖数量已达上限");
            return resultJson;
        }
        boolean isSticky = stickyService.whetherSticky(topicId)==null?false:true;
        if (isSticky) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("该帖子已是置顶帖，请勿重复设置");
            return resultJson;
        }

        Date date = new Date();
        StickyEntity stickyEntity = new StickyEntity(topicId, date.getTime()/1000+"");
        int result = stickyService.addNewSticky(stickyEntity);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }
        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员撤销置顶
     * @param topicId
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteSticky", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson deleteStickyTopics(@RequestParam int topicId,
                                         HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }
        if (stickyService.whetherSticky(topicId)==null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("该帖子不是置顶帖，无法撤销置顶");
            return resultJson;
        }
        int result = stickyService.deleteSticky(topicId);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员设置论坛公告
     * @param announceParam
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson publishAnnounce(@RequestBody AnnounceParam announceParam,
                                      HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }
        Date date = new Date();

        String title = announceParam.getTitle();
        String content = announceParam.getContent();
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("公告的内容和标题不允许为空");
            return resultJson;
        }

        AnnounceEntity announceEntity = new AnnounceEntity(announceParam);
        announceEntity.setAnnounceTime(date.getTime()/1000 +"");
        announceEntity.setAnnounceId(1);
        int result;
        if (announceService.getAnnounce() != null) {
            result = announceService.updateAnnounce(announceEntity);
        } else {
            result = announceService.addAnnounce(announceEntity);
        }

        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 管理员新增一个帖子分类，返回新增分类Id
     * @param categoryParam
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson addCategory(@RequestBody CategoryParam categoryParam,
                                  HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }

        String name = categoryParam.getName();
        String des = categoryParam.getDescription();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(des)) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("分类名和内容不允许为空");
            return resultJson;
        }
        CategoryEntity categoryEntity = new CategoryEntity(categoryParam);
        int result = categoryService.addNewCategory(categoryEntity);

        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(categoryEntity.getCategoryId());
        return resultJson;
    }

    /**
     * 管理员删除一种帖子分类
     * @param categoryId
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson deleteCategory(@RequestParam int categoryId,
                                     HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin==null || !(Boolean)isAdmin) {
            resultJson.setErrorCode(1008);
            resultJson.setErrorMsg("权限不足！");
            return resultJson;
        }

        if (categoryService.getCategoryById(categoryId)==null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("分类不存在，请查证");
            return resultJson;
        }
        int result = categoryService.deleteCategory(categoryId);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }
}
