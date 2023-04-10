package com.lcx.controller;


import com.github.pagehelper.PageInfo;
import com.lcx.entity.User;
import com.lcx.service.impl.UserServiceImpl;
import com.lcx.utils.ContextHolderUtil;
import com.lcx.utils.RedisUserUtil;
import com.lcx.utils.RequestUtils;
import com.lcx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RedisUserUtil redisUserUtil;

    @PostMapping("/create")
    public Result  create(@RequestBody User user){
        userServiceImpl.create(user);
        return Result.ok(user);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        userServiceImpl.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  User user){
        userServiceImpl.update(user);
        return Result.ok(user);
    }
    @PostMapping("/query")
    public Map query(@RequestBody User user){
        PageInfo<User> pageInfo = userServiceImpl.query(user);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        User detail = userServiceImpl.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody User user){
        int count = userServiceImpl.count(user);
        return Result.ok(count);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //获取文件名后缀
        String ex = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        String newFileNamePrefix= UUID.randomUUID().toString();
        String newFileName=newFileNamePrefix+"."+ex;
//        file.transferTo(new File("D:/upload/cms",newFileName));
        file.transferTo(new File("/upload/cms",newFileName));

        System.out.println(RequestUtils.getBasePath(request)+"upload/"+newFileName);
        //最后返回的是一个可以访问的全路径
        return Result.ok("https://lcx-bqy.top/cms/"+"upload/"+newFileName);
    }

    @PostMapping("/getUserMsg")
    public Result getUserMsg(){

        User userInfo = redisUserUtil.getUserInfo(User.class);
        String token = ContextHolderUtil.getTokenByHeader();
        log.info("token 信息: {}  用户信息: {}",token,userInfo);

        return Result.ok(userInfo);
    }

}

