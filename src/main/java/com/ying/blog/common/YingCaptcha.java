/**
 * Chsi
 * Created on 2020年10月29日
 */
package com.ying.blog.common;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
public class YingCaptcha {

    private static final long tokenExpiredTime = 30 * 60 * 1000L;

    public volatile static Set<String> captchaSet = ConcurrentHashMap.newKeySet();

    public static boolean isValidCaptcha(String captcha) {
        if (StringUtils.isBlank(captcha)) {
            return false;
        }
        if (captchaSet.contains(captcha)) {
            captchaSet.remove(captcha);
            return true;
        }
        return false;
    }

}
