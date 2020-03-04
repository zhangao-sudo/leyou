package com.leyou.elasticsearch.poji;

import java.util.Map;

/**
 * Created by Zhang Ao on 2020/1/24 23:16
 * Email:17863572518@163.com
 */
public class SearchRequest {
    private String key;// 搜索条件

    private Integer page;// 当前页
    private Map<String,Object> maxFilter;

    private static final Integer DEFAULT_SIZE = 20;// 每页大小，不从页面接收，而是固定大小
    private static final Integer DEFAULT_PAGE = 1;// 默认页

    public String getKey() {
        return key;
    }

    public Map<String, Object> getMaxFilter() {
        return maxFilter;
    }

    public void setMaxFilter(Map<String, Object> maxFilter) {
        this.maxFilter = maxFilter;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }
}
