package com.ying.blog.interceptor;

import com.ying.blog.pojo.UserData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserData userData = (UserData) session.getAttribute("user");
        if (userData == null) {
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
