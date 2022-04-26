package com.chuhelan.dotchat.model;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/15 9:27
 */
public class BaseInfo {
    String user_name;
    String user_gender;
    String user_profile;
    String user_location;
    Integer user_point;
    Integer user_authentication;
    Integer user_state;

    public BaseInfo(String user_name, String user_gender, String user_profile, String user_location, Integer user_point, Integer user_authentication, Integer user_state) {
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_profile = user_profile;
        this.user_location = user_location;
        this.user_point = user_point;
        this.user_authentication = user_authentication;
        this.user_state = user_state;
    }
}
