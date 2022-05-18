package com.chuhelan.dotchat.model;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/15 21:40
 */
public class MailBack {
    String user_email;
    String user_name;

    public MailBack(String user_email, String user_name) {
        this.user_email = user_email;
        this.user_name = user_name;
    }
}
