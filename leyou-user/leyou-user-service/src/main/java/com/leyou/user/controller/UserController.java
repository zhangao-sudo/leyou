package com.leyou.user.controller;

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhang Ao on 2020/1/28 15:28
 * Email:17863572518@163.com
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean>  Check(@PathVariable("data") String data,@PathVariable("type") Long type)
    {
         Boolean bool=this.userService.check(data,type);
         if(bool==null)
         {
             return ResponseEntity.badRequest().build();
         }
         return  ResponseEntity.ok(bool);
    }
    @PostMapping("send")
    public ResponseEntity<Void> SendCode(@RequestParam("phone") String phone)
    {
        this.userService.sendCode(phone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("register")
    public ResponseEntity<Void> Register(User user,@RequestParam("code")String code )
    {

        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        System.out.println(user);
            if(this.userService.register(user,code))
            {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            return ResponseEntity.badRequest().build();

    }
    @PostMapping("query")
    public ResponseEntity<User> Query(@RequestParam("username") String name,@RequestParam("password") String password)
    {
        System.out.println("开始调用");
        User user=this.userService.query(name,password);
        if(user==null)
        {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
