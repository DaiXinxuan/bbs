package com.cn.bbs.util;

import com.cn.bbs.Config;
import com.cn.bbs.exception.PictureUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by dxx on 2016/8/14.
 */
public class PictureSaveUtil {
    public static String saveAndReturnUrl(MultipartFile file) throws PictureUploadException {
        //图片上传约束定义
        long maxSize = 2100000;
        String allowExts="jpg,png,gif";
        String basePath= Config.PICPATH;

        String iconName = file.getOriginalFilename();
        String fileExt = iconName.substring(iconName.lastIndexOf(".") + 1).toLowerCase();
        if(file.getSize()>maxSize){
            throw new PictureUploadException("图片大小超标");
        }else if(!Arrays.asList(allowExts.split(",")).contains(fileExt)){
            throw new PictureUploadException("文件格式不符合要求!");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(basePath, newFileName));
        } catch (IOException e) {
            throw new PictureUploadException("文件写入失败");
        }
        return "/file/picture/"+newFileName;
    }
}
