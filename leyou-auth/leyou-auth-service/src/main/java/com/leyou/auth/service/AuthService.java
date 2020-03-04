package com.leyou.auth.service;

import com.leyou.auth.client.UserClient;
import com.leyou.auth.properties.JwtProperties;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zhang Ao on 2020/1/29 15:42
 * Email:17863572518@163.com
 */
@Service
public class AuthService {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserClient userClient;
    public String accredit(String name, String password) {
        User user = this.userClient.Query(name, password);
         if(user==null)
        {return null;}
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        try {
           return JwtUtils.generateToken(userInfo,this.jwtProperties.getPrivateKey(),this.jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
