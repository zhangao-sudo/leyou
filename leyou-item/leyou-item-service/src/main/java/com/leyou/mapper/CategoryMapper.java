package com.leyou.mapper;

import com.item.leyou.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.Mapping;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/19 14:54
 * Email:17863572518@163.com
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {

}
