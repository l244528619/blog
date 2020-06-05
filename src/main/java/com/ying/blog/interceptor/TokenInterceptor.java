package com.ying.blog.interceptor;

import static com.ying.blog.common.Constants.USER;

import com.ying.blog.pojo.UserData;
import com.ying.blog.token.TokenDto;
import com.ying.blog.token.TokenManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        } else {
            String token = request.getHeader("TOKEN");
            boolean tokenValid = tokenValid(token);
            //如果token合法
            if (tokenValid) {
                //根据token获取到user
                UserData user = tokenManager.queryUser(token);
                if (user != null) {
                    //将user 添加到 request中，以便后续操作获取user
                    request.setAttribute(USER, user);
                    return true;
                }
            }
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(String.format("{\"flag\":false,\"msg\":\"%s\"}", "您尚未登录"));
        return false;
    }

    private boolean tokenValid(String token) {
        TokenDto tokenDto = tokenManager.query(token);
        if (tokenDto == null) {
            return false;
        }
        if (tokenDto.getExpired() <= System.currentTimeMillis()) {
            return false;
        }
        return true;
    }
}
