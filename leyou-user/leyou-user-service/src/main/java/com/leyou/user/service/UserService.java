package com.leyou.user.service;

import com.leyou.common.utils.CodecUtils;
import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zhang Ao on 2020/1/28 15:27
 * Email:17863572518@163.com
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    private static final String key_predix="user:verify";
    @Autowired
    private RedisTemplate redisTemplate;


    public Boolean check(String data, Long type) {
        User Record = new User();
        if(type==1)
        {
            Record.setUsername(data);
        }else if(type==2)
        {
            Record.setPhone(data);
        }else
        {
            return null;
        }

        return this.userMapper.selectCount(Record)==0;
    }

    public void sendCode(String phone) {
        if(StringUtils.isBlank(phone))
        {
            return;
        }
        String code = NumberUtils.generateCode(6);
        Map<String,String> msg=new HashMap<>();
        msg.put("phone",phone);
        msg.put("code",code);

       System.out.println("发送");
        this.amqpTemplate.convertAndSend("leyou.sms.exchange","sms.verify.code",msg);
        this.redisTemplate.opsForValue().set(key_predix+phone,code,5, TimeUnit.MINUTES);

    }
    public Boolean register(User user,String code)
    {
       String rediscode= (String) this.redisTemplate.opsForValue().get(key_predix+user.getPhone());
        if(StringUtils.equals(code,rediscode))
        {
            String salt = CodecUtils.generateSalt();
            user.setSalt(salt);
            user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
            user.setId(null);
            user.setCreated(new Date());
            this.userMapper.insertSelective(user);
            return true;
        }
        return false;


    }

    public User query(String name, String password) {

        User user = new User();
        user.setUsername(name);
        User u=this.userMapper.selectOne(user);

        if(u==null)
        {
            return null;
        }
        String spassword=CodecUtils.md5Hex(password,u.getSalt());

        if(StringUtils.equals(spassword,u.getPassword()))
        {

            return u;
        }
        return null;
    }
}
