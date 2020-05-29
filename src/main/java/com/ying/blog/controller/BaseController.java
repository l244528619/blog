/**
 * Chsi
 * Created on 2020年05月29日
 */
package com.ying.blog.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
public class BaseController {

    protected Map error(Object msg) {
        Map map = new HashMap(2);
        map.put("flag", false);
        map.put("msg", msg);
        return map;
    }

    protected Map success(Object msg) {
        Map map = new HashMap(2);
        map.put("flag", true);
        map.put("msg", msg);
        return map;
    }
}
