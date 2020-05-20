/**
 * Chsi
 * Created on 2020年05月20日
 */
package com.ying.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lil
 * @version $
 */
@Controller
public class HomeController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
