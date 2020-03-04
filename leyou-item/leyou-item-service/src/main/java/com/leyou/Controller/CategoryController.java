package com.leyou.Controller;

import com.item.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.graalvm.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/19 15:04
 * Email:17863572518@163.com
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid",defaultValue = "0")Long pid)
    {
        try {
            if(pid==null||pid<0)
            {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }

            List<Category> categories=this.categoryService.queryCategoriesByPid(pid);
            if(categories==null||categories.size()==0)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(categories);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @GetMapping("names")
    public ResponseEntity<List<String>> findnamebyId(@RequestParam("ids") List<Long> ids)
    {
        List<String> names = this.categoryService.queryNamesByIds(ids);
        if(CollectionUtils.isEmpty(names))
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);


    }


}
