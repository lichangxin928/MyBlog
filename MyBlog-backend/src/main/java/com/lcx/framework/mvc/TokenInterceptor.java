package com.lcx.framework.mvc;

import com.lcx.entity.User;
import com.lcx.framework.exception.MyException;
import com.lcx.utils.Constants;
import com.lcx.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;
    /**
     * 检验 token 的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token=request.getHeader(Constants.TOKEN);
        User user = redisUtil.get(Constants.USER_PREFIX + token,User.class);
        if (user == null){
            throw new MyException("token 超时或者不合法");
        }
        //token续期
        redisUtil.expire(Constants.USER_PREFIX + token,Constants.TOKEN_EXPR);
        response.setHeader(Constants.TOKEN,token);
        request.setAttribute("user",user);
        return true;
    }

}
