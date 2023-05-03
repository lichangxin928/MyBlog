package com.lcx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcx.entity.Menu;
import com.lcx.entity.UserGroup;


import java.util.List;

/**
 * @author lichangxin2
 * @date 2023/3/31 21:47
 * @Description
 */
public interface UserGroupService extends IService<UserGroup> {
    List<Menu> findMenuByUserId(Integer userId);
}
