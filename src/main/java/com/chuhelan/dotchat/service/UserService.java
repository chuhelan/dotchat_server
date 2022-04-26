package com.chuhelan.dotchat.service;

import com.chuhelan.dotchat.beans.User;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 20:56
 */
public interface UserService {
    //通过手机号和昵称注册账号
    int register_user_by_userphone_and_username(User u);

    int register_from_website(User u);

    boolean verify_token_by_id(int id, String token);

    String login_user_by_mail(String user_email, String user_password);

    User get_user_by_mail(String user_email);
}
