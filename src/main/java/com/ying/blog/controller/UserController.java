/**
 * Chsi
 * Created on 2020年10月29日
 */
package com.ying.blog.controller;

import com.ying.blog.common.YingRepository;
import com.ying.blog.pojo.UserData;
import java.util.Map;
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
    public Map saveUser(@RequestParam String userName, @RequestParam String password) {
        if (YingRepository.getUserByName(userName) != null) {
            return error("用户已经存在");
        }
        YingRepository.saveUser(userName, password);
        return success("保存成功");
    }

    @ResponseBody
    @RequestMapping("updateUser")
    public Map updateUser(@RequestParam String userName, @RequestParam String password) {
        if (YingRepository.getUserByName(userName) == null) {
            return error("用户不存在");
        }
        YingRepository.updateUser(userName, password);
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
