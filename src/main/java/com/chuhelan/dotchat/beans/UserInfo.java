package com.chuhelan.dotchat.beans;

import lombok.Data;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 17:28
 */

@Data
public class UserInfo {
    private Integer user_id;
    private String user_name;
    private String user_gender;
    private String user_profile;
    private String user_location;
    private int user_point;
    private int user_authentication;
    private int user_state;
}
