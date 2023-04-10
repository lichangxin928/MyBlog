package com.lcx.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lichangxin2
 * @date 2023/3/31 20:58
 * @Description
 */

public class ContextHolderUtil {

    /**
     * SpringMvc下获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {

        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            return request;
        }
        return null;

    }

    public static HttpServletResponse getResponse() {

        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
            return response;
        }
        return null;

    }

    /**
     * SpringMvc下获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }

    /**
     * get token from request header
     *
     * @return
     */
    public static String getTokenByHeader() {
        return getRequest().getHeader(Constants.TOKEN);
    }
}
