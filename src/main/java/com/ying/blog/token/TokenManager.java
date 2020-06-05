
package com.ying.blog.token;

import com.ying.blog.pojo.UserData;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    public static final Logger logger = LoggerFactory.getLogger(TokenManager.class);


    private static final ConcurrentHashMap<String, TokenDto> tokenPool = new ConcurrentHashMap<>();

    private static final long tokenExpiredTime = 30 * 60 * 1000L;

    public TokenDto generateToken(UserData user) {
        String tokenStr = DigestUtils.md5Hex(user.toString() + System.currentTimeMillis());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setUser(user);
        tokenDto.setExpired(System.currentTimeMillis() + tokenExpiredTime);
        tokenDto.setToken(tokenStr);
        tokenPool.put(tokenStr, tokenDto);
        return tokenDto;
    }

    public TokenDto query(String token) {
        if (token == null) {
            return null;
        }
        return tokenPool.get(token);
    }

    public UserData queryUser(String token) {
        if (token == null) {
            return null;
        }
        return tokenPool.get(token) == null ? null : tokenPool.get(token).getUser();
    }

    public void deleteToken(String token) {
        if (token != null) {
            tokenPool.remove(token);
        }
    }

    /**
     * 定时清理无效token
     */
    @Scheduled(initialDelay = tokenExpiredTime, fixedDelay = tokenExpiredTime)
    private void cleanToken() {
        logger.info("开始clean token");

        tokenPool.forEach((key, value) -> {
            if (value.getExpired() <= System.currentTimeMillis()) {
                tokenPool.remove(key, value);
            }
        });
    }

}
