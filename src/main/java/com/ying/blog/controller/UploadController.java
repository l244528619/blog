package com.ying.blog.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
