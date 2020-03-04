package com.item.leyou.api;

import com.item.leyou.bo.SpuBo;
import com.item.leyou.pojo.Sku;
import com.item.leyou.pojo.Spu;
import com.item.leyou.pojo.SpuDtail;
import com.leyou.common.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/21 20:41
 * Email:17863572518@163.com
 */

public interface GoodsApi {

    @GetMapping("sku/{skuId}")
    public Sku findSkubyId(@PathVariable("skuId") Long id);

    @GetMapping("spu/page")
    public PageResult<SpuBo> querySpuBoByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows
    );
    @PostMapping("goods")
    public  Void saveGoods(@RequestBody SpuBo spuBo);

    @GetMapping("spu/detail/{spuId}")
    public  SpuDtail  findSpudetailbyid(@PathVariable("spuId") Long spuId);

   @GetMapping("sku/list")
   public  List<Sku> findSkubyid( @RequestParam("id") long  id);

    @PutMapping("goods")
    public Void updateGoods(@RequestBody SpuBo spuBo);
    @GetMapping("spu/{id}")
    public Spu findSpubyId(@PathVariable("id") Long id);
}