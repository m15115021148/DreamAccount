package com.romantic.dreamaccount.config;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class Comment {

    //http request result
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    public static int USER_ID = 0;

    public static class PrefKey{
        public static final String USER_NAME = "user_name";
        public static final String USER_PWD = "user_password";
        public static final String USER_ID = "user_id";
        public static final String LOGIN_FIRST = "login_first";
        public static final String KIND_DATA = "kind_data";
    }

}
