package com.chuhelan.dotchat.service;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.beans.UserInfo;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 20:56
 */
public interface UserService {
    //通过手机号和昵称注册账号
    int register_user_by_userphone_and_username(User u);

    int register_from_website(User u);

    //    注册用户信息 顺便填充user_info信息
    int set_userinfo_by_id_and_name(int user_id, String user_name);

    boolean verify_token_by_id(int id, String token);

    String login_user_by_mail(String user_email, String user_password);

    String login_user_by_phone(String user_phone, String user_password);

    User get_user_by_mail(String user_email);

    User get_user_by_phone(String user_phone);

    User get_user_by_id(int id);

    UserInfo get_userinfo_by_id(int id);

    String update_password(String user_password, int user_id);

    String update_userinfo(int user_id, String user_name, String user_gender, String user_profile, String user_location);

    int select_user_by_username(String user_name);

    UserInfo[] select_latest_userinfo_not_in_cookie_userid(int user_id);

    String do_follow_and_count_plus(int user_id, int follow_id);

    UserInfo[] select_all_follows(int user_id);

    UserInfo[] select_all_followers(int user_id);

    User select_user_email_and_user_name_by_userid(int user_id);

    //取消关注
    String cancel_follow_by_user_id(int mine_id, int its_id);

    UserInfo[] search_user_name_by_key_words(String wd);

    UserInfo[] get_all_follows_by_user_id(int user_id);

    UserInfo[] get_all_followers_by_user_id(int user_id);
}
