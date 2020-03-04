package com.leyou.Controller;

import com.item.leyou.pojo.Brand;
import com.leyou.common.PageResult;
import com.leyou.service.BrandService;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/19 20:29
 * Email:17863572518@163.com
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<PageResult<Brand>> addBrand( Brand brand, @RequestParam("cids") List<Long> cids)
    {
         this.brandService.addBrand(brand,cids);
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("cid/{cid}")
    public  ResponseEntity<List<Brand>> findBrandby(@PathVariable("cid") Long cid)
    {
        List<Brand> barand=brandService.findBrandbycid(cid);
        if(CollectionUtils.isEmpty(barand))
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(barand);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Brand> findBrandbyid(@PathVariable("id") Long id)
    {
        Brand barand=brandService.findBrandbyid(id);
        if(barand==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(barand);

    }
}