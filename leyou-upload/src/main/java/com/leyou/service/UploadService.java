package com.leyou.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/20 14:18
 * Email:17863572518@163.com
 */
@Service
public class UploadService {
 private  static final List<String> CONTEXT_FORM = Arrays.asList("image/jpeg", "image/gif");
 private static final Logger logger= LoggerFactory.getLogger(UploadService .class);


    public String upLoad(MultipartFile file) {
        //验证图片格式
        String filename=file.getOriginalFilename();
        String Type=file.getContentType();
       if(!CONTEXT_FORM.contains(Type))
       {
           logger.info("图片格式不对"+filename);
           return null;
       }
        //验证内容
        BufferedImage bf= null;
        try {
            bf = ImageIO.read(file.getInputStream());
            if(bf==null)
            {
                logger.info("内容不正确"+filename);
                return null;
            } //保存到服务器

            file.transferTo(new File("D:\\WorkSpas\\images\\"+filename));
             return "http://image.leyou.com/"+filename; //返回url
        } catch (IOException e) {

            logger.info("服务器错误");
            e.printStackTrace();
        }

     return null;



    }
}
