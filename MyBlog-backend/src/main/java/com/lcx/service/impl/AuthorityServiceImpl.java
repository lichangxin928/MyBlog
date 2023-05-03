package com.lcx.service.impl;

import com.lcx.entity.Menu;
import com.lcx.entity.User;
import com.lcx.service.AuthorityService;
import com.lcx.service.UserGroupService;
import com.lcx.utils.RedisUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lichangxin2
 * @date 2023/4/11 15:55
 * @Description
 */
@Service
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    RedisUserUtil redisUserUtil;

    @Autowired
    UserGroupService userGroupService;

    @Override
    public List<Menu> getMenu() {
        User userInfo = redisUserUtil.getUserInfo(User.class);
        log.info("##### 用户信息: {}",userInfo);
        return userGroupService.findMenuByUserId(userInfo.getId());
    }
}
