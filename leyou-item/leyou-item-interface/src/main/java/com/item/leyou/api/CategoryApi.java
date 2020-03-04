package com.item.leyou.api;

import com.item.leyou.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/19 15:04
 * Email:17863572518@163.com
 */

@RequestMapping("category")
public interface CategoryApi  {

    @GetMapping("list")
    public List<Category> queryCategoryByPid(@RequestParam(value = "pid",defaultValue = "0")Long pid);

    @GetMapping("names")
    public List<String> findnamebyId(@RequestParam("ids") List<Long> ids);


}
