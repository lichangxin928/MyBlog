package com.lcx.cms;

import com.lcx.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lichangxin2
 * @date 2023/3/31 17:18
 * @Description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void redisTest(){
        redisUtil.set("test","{}");
    }
}
