package com.course.system.interceptor;

import com.course.server.exception.BusinessException;
import com.course.server.exception.BusinessExceptionCode;
import com.course.server.service.UserService;
import com.course.server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: miaogang
 * @Date: 2020年11月04日
 * @Description: Token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (authHeader == null || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        //取得token
        String token = authHeader.substring(7);

        //验证token
        boolean verify = JwtUtil.verify(token);
        if (!verify) {
            throw new BusinessException(BusinessExceptionCode.USER_NOT_LOGIN);
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}

