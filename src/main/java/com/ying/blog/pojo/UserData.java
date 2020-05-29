/**
 * Chsi
 * Created on 2020年05月29日
 */
package com.ying.blog.pojo;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
public class UserData {

    public UserData() {

    }

    public UserData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String userName;
    public String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
