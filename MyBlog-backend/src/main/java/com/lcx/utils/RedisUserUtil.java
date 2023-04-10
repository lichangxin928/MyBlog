package com.lcx.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lichangxin2
 * @date 2023/3/31 20:57
 * @Description
 */
@Component
public class RedisUserUtil {

    @Autowired
    RedisUtil redisUtil;

    public <T> T getUserInfo(Class<T> clazz) {
        HttpServletRequest request = ContextHolderUtil.getRequest();
        if (null != request) {
            String token = request.getHeader(Constants.TOKEN);
            String json = redisUtil.get(Constants.USER_PREFIX + token);
            if (null != json) {
                return JSON.parseObject(json, clazz);
            }
        }
        return null;
    }

}
