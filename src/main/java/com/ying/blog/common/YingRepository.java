/**
 * Chsi
 * Created on 2020年10月29日
 */
package com.ying.blog.common;

import com.ying.blog.pojo.BaseData;
import com.ying.blog.pojo.UserData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
public class YingRepository {

    public volatile static Map<String, List<? extends BaseData>> database = new ConcurrentHashMap();

    static {
        List<UserData> userDatas = new ArrayList<>();
        userDatas.add(new UserData("admin", "123456", "18810112022"));
        database.put(UserData.class.getName(), userDatas);
    }

    public static List<UserData> getAllUser() {
        return (List<UserData>) database.get(UserData.class.getName());
    }

    public static UserData getUserByName(String userName) {
        for (UserData userData : (List<UserData>) database.get(UserData.class.getName())) {
            if (userData.getUserName().equals(userName)) {
                return userData;
            }
        }
        return null;
    }

    public static UserData getUserByMobile(String mobile) {
        for (UserData userData : (List<UserData>) database.get(UserData.class.getName())) {
            if (userData.getMobile().equals(mobile)) {
                return userData;
            }
        }
        return null;
    }

    public static void saveUser(String userName, String password, String mobile) {
        ((List<UserData>) database.get(UserData.class.getName())).add(new UserData(userName, password, mobile));
    }

    public static void updateUserPassword(String userName, String password) {
        getUserByName(userName).setPassword(password);
    }

    public static void updateUserMobile(String userName, String mobile) {
        getUserByName(userName).setMobile(mobile);
    }

    public static void deleteUser(String userName) {
        UserData data = getUserByName(userName);
        if (data != null) {
            database.get(UserData.class.getName()).remove(data);
        }
    }

    public static boolean isValidUser(String userName, String password) {
        for (UserData userData : (List<UserData>) database.get(UserData.class.getName())) {
            if (userData.getUserName().equals(userName) && userData.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
