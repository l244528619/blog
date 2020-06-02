package com.ying.blog.controller;

import com.ying.blog.pojo.UserData;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lil
 * @version $
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("currentLoginUser")
    public Map currentLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserData userData = (UserData) session.getAttribute("user");
        if (userData == null) {
            return error("当前用户未登录");
        }
        return success(userData);
    }

    @ResponseBody
    @RequestMapping("login")
    public Map login(@RequestParam(required = false) String userName,
            @RequestParam(required = false) String password,
            HttpServletRequest request) {
        if (StringUtils.isBlank(userName)) {
            return error("登录名不能为空");
        }

        if (StringUtils.isBlank(password)) {
            return error("密码不能为空");
        }
        if (!userName.equals("admin") || !password.equals("123456")) {
            return error("用户名或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", new UserData(userName, password));
        UserData userData = new UserData();
        userData.setUserName(userName);
        return success(userData);
    }

    @ResponseBody
    @RequestMapping("logout")
    public Map logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return success("退出登录成功");
    }
}
