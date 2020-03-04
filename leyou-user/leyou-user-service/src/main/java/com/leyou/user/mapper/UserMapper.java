package com.leyou.user.mapper;
import com.leyou.user.pojo.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Zhang Ao on 2020/1/28 15:21
 * Email:17863572518@163.com
 */

public interface UserMapper extends Mapper<User> {
}
