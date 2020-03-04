package com.leyou.Controller;

import com.item.leyou.pojo.SpecGroup;
import com.item.leyou.pojo.SpecParam;
import com.leyou.service.SpecGroupAndParamService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/21 19:17
 * Email:17863572518@163.com
 */
@RestController
@RequestMapping("spec")
public class SpecficationContorller {

    @Autowired
    private SpecGroupAndParamService specGroupAndParamService;

    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups = this.specGroupAndParamService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean seg

    ){
        List<SpecParam>  params = this.specGroupAndParamService.queryParams(gid,cid,seg);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
    @GetMapping("paramsSpecial")
    public ResponseEntity<List<SpecParam>> queryParamsSpecial(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean seg,
             @RequestParam(value = "gen",required = false)Boolean gen

    ){
        List<SpecParam>  params = this.specGroupAndParamService.queryParamsSpecial(gid,cid,seg,gen);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
}

