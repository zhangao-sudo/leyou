package com.item.leyou.api;

import com.item.leyou.pojo.SpecGroup;
import com.item.leyou.pojo.SpecParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/21 19:17
 * Email:17863572518@163.com
 */
;
@RequestMapping("spec")
public interface SpecficationApi {


    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public List<SpecGroup> queryGroupsByCid(@PathVariable("cid")Long cid);

    @GetMapping("params")
    public List<SpecParam> queryParams(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean seg

    );
    @GetMapping("paramsSpecial")
    public List<SpecParam> queryParamsSpecial(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean seg,
            @RequestParam(value = "gen",required = false)Boolean gen

    );
}

