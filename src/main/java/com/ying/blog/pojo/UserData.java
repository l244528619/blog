package com.ying.blog.pojo;

public class UserData extends BaseData {

    public UserData() {

    }

    public UserData(String userName, String password, String mobile) {
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
    }

    private String userName;
    private String password;
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
