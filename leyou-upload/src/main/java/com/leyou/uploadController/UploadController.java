package com.leyou.uploadController;

import com.leyou.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zhang Ao on 2020/1/20 14:08
 * Email:17863572518@163.com
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping("image")
    public ResponseEntity<String> Upload(MultipartFile file)
    {
        String url=uploadService.upLoad(file);
        if(url==null)
        {
            System.out.println("forbidden");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
