package com.lcx.framework.mvc;

import com.lcx.entity.User;
import com.lcx.framework.exception.MyException;
import com.lcx.framework.jwt.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

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

      String token=request.getHeader(JWTUtils.token);
        User user = JWTUtils.getUser(token);
        if (user == null){
            throw new MyException("token 超时或者不合法");
        }
        //token续期
        String newToken = JWTUtils.sign(user);
        response.setHeader(JWTUtils.token,newToken);
        request.setAttribute("user",user);
        return true;
    }

}
