package com.leyou.elasticsearch.poji;

import com.item.leyou.pojo.Brand;
import com.leyou.common.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhang Ao on 2020/1/25 18:51
 * Email:17863572518@163.com
 */
public class SearchResult extends PageResult<Goods> {

    private List<Map<String, Object>> categories;
    private List<Brand> brands;
    private List<Map<String, Object>> spec;

    public SearchResult() {
    }

    public List<Map<String, Object>> getSpec() {
        return spec;
    }

    public void setSpec(List<Map<String, Object>> spec) {
        this.spec = spec;
    }

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<Map<String, Object>> categories, List<Brand> brands, List<Map<String, Object>> spec) {
        super( total, new Long(totalPage),items);
        this.categories = categories;
        this.brands = brands;
        this.spec=spec;
    }

    public List<Map<String, Object>> getCategories() {
        return categories;
    }

    public void setCategories(List<Map<String, Object>> categories) {
        this.categories = categories;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}