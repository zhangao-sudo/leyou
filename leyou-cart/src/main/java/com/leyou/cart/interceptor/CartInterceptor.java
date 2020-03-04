package com.leyou.cart.interceptor;

import com.leyou.cart.properties.JwtProperties;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhang Ao on 2020/1/30 17:05
 * Email:17863572518@163.com
 */
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class CartInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtProperties jwtProperties;
    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          String token= CookieUtils.getCookieValue(request,this.jwtProperties.getCookieName());
          UserInfo userInfo= JwtUtils.getInfoFromToken(token,this.jwtProperties.getPublicKey());
          if(userInfo==null)
          {
              return false;
          }
          THREAD_LOCAL.set(userInfo);
          return true;
    }
    public static UserInfo getUserInfo()
    {
        return  THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.remove();
    }
}
