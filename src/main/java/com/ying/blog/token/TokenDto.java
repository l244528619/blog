
package com.ying.blog.token;

import com.ying.blog.pojo.UserData;
import java.io.Serializable;

public class TokenDto implements Serializable {

    private String token;
    private long expired;
    private UserData user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                ", expired=" + expired +
                ", user=" + user +
                '}';
    }
}
