package com.lcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcx.entity.Menu;
import com.lcx.entity.UserGroup;
import com.lcx.mapper.UserGroupMapper;
import com.lcx.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author lichangxin2
 * @date 2023/3/31 21:53
 * @Description
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper,UserGroup> implements UserGroupService {

    @Autowired
    UserGroupMapper userGroupMapper;

    @Override
    public List<Menu> findMenuByUserId(Integer userId){
        return userGroupMapper.findMenuByUserId(userId);
    }
}
