package com.chuhelan.dotchat.dao.impl;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.beans.UserInfo;
import com.chuhelan.dotchat.dao.UserDao;

import java.util.Date;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 17:34
 */
public class UserDaoImpl implements UserDao {

    @Override
    public int register_user_by_userphone_and_username(User u) {
        return 0;
    }

    @Override
    public int register_from_website(User u) {
        return 0;
    }

    @Override
    public User select_user_by_email_and_password(User u) {
        return null;
    }

    @Override
    public User get_user_by_mail(String user_email) {
        return null;
    }

    @Override
    public User get_user_by_id(int id) {
        return null;
    }

    @Override
    public UserInfo get_userinfo_by_id(int id) {
        return null;
    }

    @Override
    public int set_userinfo_by_id_and_name(int user_id, String user_name) {
        return 0;
    }

    @Override
    public int save_user_token_by_id(int id, String token) {
        return 0;
    }

    @Override
    public int save_user_token_die_time_by_id(int id, Date date) {
        return 0;
    }

    @Override
    public int set_userinfo_by_userid(int id) {
        return 0;
    }

    @Override
    public int update_password(String user_password,int user_id) {
        return 0;
    }

    @Override
    public int update_userinfo(int user_id, String user_name, String user_gender, String user_profile, String user_location) {
        return 0;
    }

    @Override
    public int update_username_by_id(int user_id, String user_name) {
        return 0;
    }

    @Override
    public int select_user_by_username(String user_name) {
        return 0;
    }

    @Override
    public UserInfo[] select_latest_userinfo_not_in_cookie_userid(int user_id) {
        return new UserInfo[0];
    }

    @Override
    public int do_follow_and_count_plus(int user_id, int follow_id) {
        return 0;
    }

    @Override
    public int do_user_id_count_follows_plus(int user_id) {
        return 0;
    }

    @Override
    public int do_follower_id_count_followers_plus(int follow_id) {
        return 0;
    }

    @Override
    public UserInfo[] select_all_follows(int user_id) {
        return new UserInfo[0];
    }

    @Override
    public UserInfo[] select_all_followers(int user_id) {
        return new UserInfo[0];
    }

    @Override
    public User select_user_email_and_user_name_by_userid(int user_id) {
        return null;
    }


}
