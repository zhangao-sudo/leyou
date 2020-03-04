package com.leyou.auth.Controller;

import com.leyou.auth.properties.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhang Ao on 2020/1/29 15:37
 * Email:17863572518@163.com
 */
@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("accredit")
    public ResponseEntity<Void> accredit(@RequestParam("username") String name, @RequestParam("password") String password,
                                         HttpServletRequest request,
                                         HttpServletResponse response)
    {

        String token=this.authService.accredit(name,password);

        if(StringUtils.isBlank(token))
        {
            return ResponseEntity.badRequest().build();
        }
        CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token,this.jwtProperties.getExpire()*60);
        return  ResponseEntity.ok(null);
    }
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verity(@CookieValue("LY_TOKEN")String token, HttpServletRequest request, HttpServletResponse response)
    {
        UserInfo infoFromToken = null;
        try {
            infoFromToken = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            if(infoFromToken==null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            }

            String token2=JwtUtils.generateToken(infoFromToken,this.jwtProperties.getPrivateKey(),this.jwtProperties.getExpire());
            if(StringUtils.equals(token,token2))
            {
                System.out.println("真的");
            }
            CookieUtils.setCookie(request,response,this.jwtProperties.getCookieName(),token2,this.jwtProperties.getExpire()*60);

            return ResponseEntity.ok(infoFromToken);
        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



    }

}
