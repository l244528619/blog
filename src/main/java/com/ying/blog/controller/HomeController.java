package com.ying.blog.controller;

import static com.ying.blog.common.Constants.USER;

import com.ying.blog.pojo.UserData;
import com.ying.blog.token.TokenDto;
import com.ying.blog.token.TokenManager;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lil
 * @version $
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("test")
    public String test(String token, Model model) {
        model.addAttribute("token", token);
        return "test";
    }

    @ResponseBody
    @RequestMapping("/api/currentLoginUser")
    public Map currentLoginUser(HttpServletRequest request) {
        return success(request.getAttribute(USER));
    }

    @ResponseBody
    @RequestMapping("login")
    public Map login(@RequestParam(required = false) String userName,
            @RequestParam(required = false) String password) {
        if (StringUtils.isBlank(userName)) {
            return error("登录名不能为空");
        }

        if (StringUtils.isBlank(password)) {
            return error("密码不能为空");
        }
        if (!userName.equals("admin") || !password.equals("123456")) {
            return error("用户名或密码错误");
        }
        UserData userData = new UserData();
        userData.setUserName(userName);
        TokenDto tokenDto = tokenManager.generateToken(userData);
        logger.info("当前登录用户：" + tokenDto);
        return success(tokenDto.getToken());
    }

    @ResponseBody
    @RequestMapping("logout")
    public Map logout(HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        TokenDto tokenDto = tokenManager.query(token);
        if (tokenDto == null) {
            return error("退出失败");
        }
        tokenManager.deleteToken(token);
        return success("退出登录成功");
    }
}
