package com.chuhelan.dotchat.model;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/7 19:56
 */
public class BaseId {
    String user_name;
    int user_id;

    public BaseId(String user_name, int user_id) {
        this.user_name = user_name;
        this.user_id = user_id;
    }
}
