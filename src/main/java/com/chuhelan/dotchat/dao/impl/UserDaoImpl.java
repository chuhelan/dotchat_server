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

}
