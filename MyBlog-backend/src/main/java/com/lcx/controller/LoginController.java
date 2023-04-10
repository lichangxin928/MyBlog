package com.lcx.controller;

import cn.hutool.core.util.IdUtil;
import com.lcx.entity.User;
import com.lcx.service.impl.UserServiceImpl;
import com.lcx.utils.Constants;
import com.lcx.utils.Maps;
import com.lcx.utils.RedisUtil;
import com.lcx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private UserServiceImpl userService;



    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> paramMap){
        String userName=paramMap.get("userName");
        String password=paramMap.get("password");

        System.out.println(userName+":"+password);
        User user = userService.login(userName, password);

        log.info("登录的用户信息: {}",user);

        if (user!= null){
            String token = IdUtil.simpleUUID();
            redisUtil.set(Constants.USER_PREFIX + token,user,Constants.TOKEN_EXPR);
            if (user.getStatus().equals("F")){
                return Result.fail("您的账号已被禁用");
            }
            if (user.getStatus().equals("D")){
                return Result.fail("您的账号已被设置为删除");
            }
            return Result.ok(Maps.build().put("token",token).put("user",user).getMap());
        }else{
            return Result.fail("用户名或密码错误");
        }

    }


}
