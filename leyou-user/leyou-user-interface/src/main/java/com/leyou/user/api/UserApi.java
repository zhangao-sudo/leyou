package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Zhang Ao on 2020/1/28 15:28
 * Email:17863572518@163.com
 */
@Controller
public interface UserApi {

    @GetMapping("/check/{data}/{type}")
    public Boolean  Check(@PathVariable("data") String data,@PathVariable("type") Long type);

    @PostMapping("send")
    public Void SendCode(@RequestParam("phone") String phone);

    @PostMapping("register")
    public Void Register(User user,@RequestParam("code")String code );

    @PostMapping("query")
    public User Query(@RequestParam("username") String name,@RequestParam("password") String password);

}
