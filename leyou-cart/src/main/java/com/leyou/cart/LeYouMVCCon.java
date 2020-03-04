package com.leyou.cart;

import com.leyou.cart.interceptor.CartInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Zhang Ao on 2020/1/30 17:27
 * Email:17863572518@163.com
 */
@Configuration
public class LeYouMVCCon implements WebMvcConfigurer {
    @Autowired
    private CartInterceptor cartInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor).addPathPatterns("/**");
    }
}
