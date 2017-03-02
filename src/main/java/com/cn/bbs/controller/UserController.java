package com.cn.bbs.controller;

import com.cn.bbs.database.model.UserEntity;
import com.cn.bbs.exception.PictureUploadException;
import com.cn.bbs.param.LoginParam;
import com.cn.bbs.result.ResultJson;
import com.cn.bbs.servie.UserService;
import com.cn.bbs.util.PictureSaveUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dxx on 2017/2/24.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    Log log = LogFactory.getLog(UserController.class);

    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson loginAndSave(@RequestBody LoginParam loginParam,HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        String userId = loginParam.getUserId();
        String password = loginParam.getPassword();

        //进行用户校验
        boolean verifyResult = true;
        if (!verifyResult) {
            resultJson.setErrorCode(1005);
            resultJson.setErrorMsg("用户密码不正确！");
            return resultJson;
        }

        String nickName = "";
        String iconUrl="";

        //如果校验成功且数据库尚未有该用户信息，进行昵称和头像存储。
        UserEntity userEntity = userService.getUserInfoById(userId);
        if (userEntity == null) {
            userEntity = new UserEntity(userId,nickName,iconUrl);
            int result = userService.addNewUser(userEntity);
            if (result != 1) {
                log.info("存储用户信息失败！用户id:"+userId+" 用户昵称:"+
                        nickName+" 头像url:"+iconUrl);
            }
        }
        request.getSession().setAttribute("userId", userId);

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 用户更改头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateImage",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson updateUserIcon(@RequestParam MultipartFile file,
                                     HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        String userId = (String) request.getSession().getAttribute("userId");
        if (userService.getUserInfoById(userId) == null) {
            resultJson.setErrorCode(1006);
            resultJson.setErrorMsg("session已过期，请重新登录");
            return resultJson;
        }
        if (file == null) {
            resultJson.setErrorCode(1003);
            resultJson.setErrorMsg("未检测到传入文件");
            return resultJson;
        }
        String url="";
        try {
            url = PictureSaveUtil.saveAndReturnUrl(file);
        } catch (PictureUploadException e) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg(e.getMessage());
            return resultJson;
        }
        int result = userService.updateUserIcon(url, userId);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库改动失败");
            return resultJson;
        }
        resultJson.setStatus(true);
        resultJson.setResultObj(url);
        return resultJson;
    }

    /**
     * 用户更改用户信息
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson updateUserInfo(HttpServletRequest request,
                                     @RequestBody Map<String,String> map) {
        ResultJson resultJson = new ResultJson(false);
        String userId = (String) request.getSession().getAttribute("userId");
        UserEntity userEntity = userService.getUserInfoById(userId);
        if (userEntity == null) {
            resultJson.setErrorCode(1006);
            resultJson.setErrorMsg("session已过期，请重新登录");
            return resultJson;
        }
        String nickName = map.get("nickName");
        userEntity.setNickName(nickName);
        int result = userService.updateUserInfo(userEntity);
        if (result != 1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("数据库改动失败");
            return resultJson;
        }
        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 用户注销登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson logOut(HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(true);
        request.getSession().removeAttribute("userId");
        request.getSession().invalidate();
        return resultJson;
    }


}
