package com.item.leyou.bo;

import com.item.leyou.pojo.Sku;
import com.item.leyou.pojo.Spu;
import com.item.leyou.pojo.SpuDtail;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/21 20:38
 * Email:17863572518@163.com
 */
public class SpuBo extends Spu {
    private String cname;
    private String bname;
    SpuDtail spuDetail;// 商品详情
    List<Sku> skus;// sku列表

    public SpuDtail getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(SpuDtail spuDetail) {
        this.spuDetail = spuDetail;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }


}
