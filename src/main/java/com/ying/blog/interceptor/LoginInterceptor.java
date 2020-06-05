package com.ying.blog.interceptor;

import com.ying.blog.token.TokenDto;
import com.ying.blog.token.TokenManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("TOKEN");
        TokenDto tokenDto = tokenManager.query(token);
        if (tokenDto == null) {
            String requestType = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestType)) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(String.format("{\"flag\":false,\"msg\":\"%s\"}", "您尚未登录"));
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("请登录后重试");
            }
            return false;
        }
        return true;
    }
}
