package com.leyou.Controller;
import com.item.leyou.bo.SpuBo;
import com.item.leyou.pojo.Category;
import com.item.leyou.pojo.Sku;
import com.item.leyou.pojo.Spu;
import com.item.leyou.pojo.SpuDtail;
import com.leyou.common.PageResult;
import com.leyou.service.CategoryService;
import com.leyou.service.GoodsService;
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
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuBoByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows
    ){
        PageResult<SpuBo> pageResult = this.goodsService.querySpuBoByPage(key, saleable, page, rows);
        if(CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }
    @PostMapping("goods")
    public  ResponseEntity<Void>  saveGoods(@RequestBody SpuBo spuBo)
    {
          this.goodsService.saveGoods(spuBo);
          return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("spu/detail/{spuId}")
    public  ResponseEntity<SpuDtail>  findSpudetailbyid(@PathVariable("spuId") Long spuId)
    {
        SpuDtail spuDetail=this.goodsService.findSpudetailbyid(spuId);
        if (spuDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }
   @GetMapping("sku/list")
   public  ResponseEntity<List<Sku>> findSkubyid( @RequestParam("id") long  id)
   {
            List<Sku> list=this.goodsService.findSkubyid(id);
            if(CollectionUtils.isEmpty(list))
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }
            return ResponseEntity.ok(list);
   }
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        this.goodsService.updateGoods(spuBo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

   @GetMapping("spu/{id}")
    public ResponseEntity<Spu> findSpubyId(@PathVariable("id") Long id)
   {
        Spu spu=this.goodsService.findaSpubyId(id);
        if(spu==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(spu);
   }
   @GetMapping("sku/{skuId}")
   public ResponseEntity<Sku> findSkubyId(@PathVariable("skuId") Long id)
   {
       Sku sku = this.goodsService.querySkubyid(id);
       return ResponseEntity.ok(sku);
   }
}