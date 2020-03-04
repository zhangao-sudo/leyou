package com.leyou.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.item.leyou.bo.SpuBo;
import com.item.leyou.pojo.*;
import com.leyou.common.PageResult;
import com.leyou.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhang Ao on 2020/1/21 20:42
 * Email:17863572518@163.com
 */
@Service
public class GoodsService {

    @Autowired
    private  StockMapper stockMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 搜索条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        // 分页条件
        PageHelper.startPage(page, rows);

        // 执行查询
        List<Spu> spus = this.spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);

        List<SpuBo> spuBos = new ArrayList<>();
        spus.forEach(spu->{
            SpuBo spuBo = new SpuBo();
            // copy共同属性的值到新的对象
            BeanUtils.copyProperties(spu, spuBo);
            // 查询分类名称
            List<String> names = this.categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "/"));

            // 查询品牌的名称
            spuBo.setBname(this.brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());

            spuBos.add(spuBo);
        });

        return new PageResult<>(pageInfo.getTotal(), spuBos);

    }
    @Transactional
    public void saveGoods(SpuBo spuBo) {
        //添加spu

        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        this.spuMapper.insertSelective(spuBo);



        //spu——detail
        SpuDtail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
      this.spuDetailMapper.insertSelective(spuDetail);
        //sku
        saveSkuAndStock(spuBo);



    }
   public void  saveSkuAndStock(SpuBo spuBo)
    {
        List<Sku> skus =spuBo.getSkus();
        skus.forEach(sku -> {
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insertSelective(sku);
            //socket
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        });
        sendMessage(spuBo.getId(),"insert");
    }
    public SpuDtail findSpudetailbyid(Long spuId) {

       return this.spuDetailMapper.selectByPrimaryKey(spuId);
    }

    public List<Sku> findSkubyid(long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> list= this.skuMapper.select(sku);

        list.forEach(sku1 -> {

            Stock stock = this.stockMapper.selectByPrimaryKey(sku1.getId());
            sku1.setStock(stock.getStock());

        });
        return list;
    }
    @Transactional
    public void updateGoods(SpuBo spu) {
        // 查询以前sku
        List<Sku> skus = this.findSkubyid(spu.getId());
        // 如果以前存在，则删除
        if(!CollectionUtils.isEmpty(skus)) {
            List<Long> ids = skus.stream().map(s -> s.getId()).collect(Collectors.toList());
            // 删除以前库存
            Example example = new Example(Stock.class);
            example.createCriteria().andIn("skuId", ids);
            this.stockMapper.deleteByExample(example);

            // 删除以前的sku
            Sku record = new Sku();
            record.setSpuId(spu.getId());
            this.skuMapper.delete(record);

        }
        // 新增sku和库存
        saveSkuAndStock(spu);

        // 更新spu
        spu.setLastUpdateTime(new Date());
        spu.setCreateTime(null);
        spu.setValid(null);
        spu.setSaleable(null);
        this.spuMapper.updateByPrimaryKeySelective(spu);

        // 更新spu详情
        this.spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        sendMessage(spu.getId(),"update");
    }

    public Spu findaSpubyId(Long id) {
         return this.spuMapper.selectByPrimaryKey(id);
    }
    private void sendMessage(Long id, String type){
        // 发送消息
        try {
            this.amqpTemplate.convertAndSend("item." + type, id);
        } catch (Exception e) {
           System.out.println("{}商品消息发送异常，商品id：{}"+ type+ id+e);
        }
    }

    public Sku querySkubyid(Long id) {
        Sku sku = this.skuMapper.selectByPrimaryKey(id);
        return sku;

    }
}