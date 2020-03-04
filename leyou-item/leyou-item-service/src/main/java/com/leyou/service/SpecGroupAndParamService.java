package com.leyou.service;

import com.item.leyou.pojo.SpecGroup;
import com.item.leyou.pojo.SpecParam;
import com.leyou.mapper.SpecGroupMapper;
import com.leyou.mapper.SpecParamMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhang Ao on 2020/1/21 19:14
 * Email:17863572518@163.com
 */
@Service
public class SpecGroupAndParamService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;


    public List<SpecGroup> queryGroupsByCid(Long cid) {

               SpecGroup record=new SpecGroup();
               record.setCid(cid);
                 List<SpecGroup> list= this.specGroupMapper.select(record);
                 list.forEach(a->{
                    a.setParams(this.queryParams(null,a.getCid(),null));
                 });
                 return list;

    }

    public List<SpecParam> queryParams(Long gid, Long cid,Boolean seg) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(seg);
        return this.specParamMapper.select(param);
    }

    public List<SpecParam> queryParamsSpecial(Long gid, Long cid, Boolean seg, Boolean gen) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(seg);
        param.setGeneric(gen);
        return this.specParamMapper.select(param);
    }
}
