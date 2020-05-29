/**
 * Chsi
 * Created on 2020年05月24日
 */
package com.ying.blog.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $ 2020年05月24日
 */
@Controller
@RequestMapping("api/upload")
public class UploadController {

    @RequestMapping("index")
    public String index() {
        return "upload/index";
    }

    @ResponseBody
    @RequestMapping("doUpload")
    public Map upload(MultipartFile file) {
        System.out.println("111");
        Map map = new HashMap();
        map.put("msg", "上传成功了");
        return map;
    }
}
