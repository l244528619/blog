/**
 * Chsi
 * Created on 2020年10月29日
 */
package com.ying.blog.controller;

import static com.ying.blog.common.Constants.SESSION_CAPTCHA_NAME;

import com.ying.blog.common.YingRepository;
import com.ying.blog.pojo.UserData;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
@Controller
@RequestMapping("api/user")
public class UserController extends BaseController {

    @ResponseBody
    @RequestMapping("getAllUser")
    public Map index() {
        return success(YingRepository.getAllUser());
    }

    @ResponseBody
    @RequestMapping("getUserByName")
    public Map getUserByName(@RequestParam String userName) {
        UserData userData = YingRepository.getUserByName(userName);
        return success(userData == null ? "" : userData);
    }

    @ResponseBody
    @RequestMapping("saveUser")
    public Map saveUser(@RequestParam String userName,
            @RequestParam String password,
            @RequestParam String mobile,
            @RequestParam String captcha,
            HttpServletRequest request,
            HttpServletResponse response) {
        String captSession = (String) request.getSession().getAttribute(SESSION_CAPTCHA_NAME);
        boolean valid = StringUtils.equalsIgnoreCase(captSession, captcha);
        if (!valid) {
            return error("验证码有误");
        }
        //如果验证成功且remove=true则清掉cookie
        if (valid) {
            request.getSession().removeAttribute(SESSION_CAPTCHA_NAME);
        }

        if (YingRepository.getUserByName(userName) != null) {
            return error("该用户名已经注册");
        }
        if (YingRepository.getUserByMobile(mobile) != null) {
            return error("该手机号已经注册");
        }
        YingRepository.saveUser(userName, password, mobile);
        return success("保存成功");
    }

    @ResponseBody
    @RequestMapping("updateUserPassword")
    public Map updateUserPassword(@RequestParam String userName, @RequestParam String password) {
        if (YingRepository.getUserByName(userName) == null) {
            return error("用户不存在");
        }
        YingRepository.updateUserPassword(userName, password);
        return success("更新成功");
    }

    @ResponseBody
    @RequestMapping("updateUserMobile")
    public Map updateUserMobile(@RequestParam String userName, @RequestParam String mobile) {
        if (YingRepository.getUserByName(userName) == null) {
            return error("用户不存在");
        }
        YingRepository.updateUserMobile(userName, mobile);
        return success("更新成功");
    }

    @ResponseBody
    @RequestMapping("deleteUser")
    public Map deleteUser(@RequestParam String userName) {
        if (YingRepository.getUserByName(userName) == null) {
            return error("用户不存在");
        }
        YingRepository.deleteUser(userName);
        return success("删除成功");
    }

}
