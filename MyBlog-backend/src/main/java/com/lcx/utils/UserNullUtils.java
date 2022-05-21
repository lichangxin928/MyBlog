package com.lcx.utils;

import com.lcx.entity.User;

public class UserNullUtils {

    public static User userIsNull(){
        User nullUser = new User();
        nullUser.setUserName("aaaaa");
        nullUser.setNickName("匿名");
        return nullUser;
    }
}
