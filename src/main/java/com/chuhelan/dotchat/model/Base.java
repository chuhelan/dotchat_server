package com.chuhelan.dotchat.model;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/6 21:49
 */
public class Base {
    String user_email;
    String user_phonenumber;

    public Base(String user_name, String user_phonenumber) {
        this.user_email = user_name;
        this.user_phonenumber = user_phonenumber;
    }
}
