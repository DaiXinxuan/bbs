package com.cn.bbs.controller;

import com.cn.bbs.database.model.InformEntity;
import com.cn.bbs.database.model.UserEntity;
import com.cn.bbs.result.ResultJson;
import com.cn.bbs.servie.InformService;
import com.cn.bbs.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dxx on 2017/2/27.
 */
@Controller
@RequestMapping("/inform")
public class InformController {
    @Autowired
    private InformService informService;
    @Autowired
    private UserService userService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    /**
     * 用户查看接收到的所有通知
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewNotice", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson viewNotifications(HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        String userId = (String)
                request.getSession().getAttribute("userId");
        UserEntity userEntity = userService.getUserInfoById(userId);
        if (userEntity == null) {
            resultJson.setErrorCode(1006);
            resultJson.setErrorMsg("session已过期，请重新登录");
            return resultJson;
        }
        ArrayList<InformEntity> informEntities = (ArrayList<InformEntity>)
                informService.getInformsByUserId(userId);
        //将InformEntity中的回复时间从时间戳变为标准格式
        if (informEntities!=null&&informEntities.size()>0) {
            for (InformEntity informEntity:informEntities) {
                String dateNum = informEntity.getInformDate();
                String fomat = dateFormat.format(new Date(Long.parseLong(dateNum)*1000));
                informEntity.setInformDate(fomat);
            }
        }

        resultJson.setStatus(true);
        resultJson.setResultObj(informEntities);
        return resultJson;
    }

    /**
     * 用户更改它所接收到某条通知的阅读状态
     * @param informId
     * @return
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson updateInformStatus(@RequestParam int informId) {
        ResultJson resultJson = new ResultJson(false);

        int result = informService.updateInformReadStatus(informId);
        if (result!=1) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("更改通知阅读状态时出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    /**
     * 用户清空通知栏
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteInforms", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson deleteAllInforms(HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);

        String userId = (String)
                request.getSession().getAttribute("userId");
        UserEntity userEntity = userService.getUserInfoById(userId);
        if (userEntity == null) {
            resultJson.setErrorCode(1006);
            resultJson.setErrorMsg("session已过期，请重新登录");
            return resultJson;
        }

        ArrayList<InformEntity> informEntities = (ArrayList<InformEntity>)
                informService.getInformsByUserId(userId);
        if (informEntities == null) {
            resultJson.setErrorCode(1002);
            resultJson.setErrorMsg("你没有任何通知，无法删除");
            return resultJson;
        }

        int result = informService.deleteInforms(userId);
        if (result != informEntities.size()) {
            resultJson.setErrorCode(1001);
            resultJson.setErrorMsg("删除通知时出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }
}
