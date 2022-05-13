package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.beans.UserInfo;
import com.chuhelan.dotchat.dao.UserCountDao;
import com.chuhelan.dotchat.dao.UserDao;
import com.chuhelan.dotchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 20:57
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserCountDao userCountDao;

    @Override
    public int register_user_by_userphone_and_username(User u) {
        userDao.register_user_by_userphone_and_username(u);
        return 200;
    }

    @Override
    public int register_from_website(User u) {

        userDao.register_from_website(u);
        System.out.println("插入的id和name：");
        System.out.println(u.getUser_id());
        System.out.println(u.getUser_name());
        int user_id = userDao.get_user_by_mail(u.getUser_email()).getUser_id();
        String user_name = u.getUser_name();
        System.out.println("++++++++++查找id和name++++++++++");
        System.out.println(user_id);
        System.out.println(user_name);
        System.out.println("===============================");
        userDao.set_userinfo_by_id_and_name(user_id, user_name);
        System.out.println("将用户信息添加到post_count表中：" + user_id);
        userCountDao.add_user_by_id(user_id);
        return 200;
    }


    @Override
    public int set_userinfo_by_id_and_name(int user_id, String user_name) {
        userDao.set_userinfo_by_id_and_name(user_id, user_name);
        return 0;
    }

    @Override
    public boolean verify_token_by_id(int id, String token) {
        System.out.println("操作 > verify_token_by_id > 验证登陆 > " + id + " / " + token);
        // 获取用户信息
        User info = userDao.get_user_by_id(id);
        //验证 token
        return info.getUser_session().equals(token) && new Date().before(info.getUser_diesession_time());
    }

    @Override
    public String update_password(String user_password, int user_id) {
        userDao.update_password(user_password, user_id);
        return "200";
    }

    @Override
    public String update_userinfo(int user_id, String user_name, String user_gender, String user_profile, String user_location) {
        userDao.update_userinfo(user_id, user_name, user_gender, user_profile, user_location);
        userDao.update_username_by_id(user_id, user_name);
        return "200";
    }

    @Override
    public int select_user_by_username(String user_name) {
        return userDao.select_user_by_username(user_name);
    }

    @Override
    public UserInfo[] select_latest_userinfo_not_in_cookie_userid(int user_id) {
        return userDao.select_latest_userinfo_not_in_cookie_userid(user_id);
    }

    @Override
    public String do_follow_and_count_plus(int user_id, int follow_id) {
        userDao.do_follow_and_count_plus(user_id, follow_id);
        userDao.do_user_id_count_follows_plus(user_id);
        userDao.do_follower_id_count_followers_plus(follow_id);
        return "200";
    }

    @Override
    public UserInfo[] select_all_follows(int user_id) {
        return userDao.select_all_follows(user_id);
    }

    @Override
    public UserInfo[] select_all_followers(int user_id) {
        return userDao.select_all_followers(user_id);
    }

    @Override
    public String login_user_by_mail(String user_email, String user_password) {
        // 获取用户信息
        User info = userDao.get_user_by_mail(user_email);
        if (info == null) return "404";        // 账户或密码不存在
        // 验证用户密码
        if (info.getUser_password().equals(user_password)) {
            // 创建 token
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            // 写入数据库
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 5);
            date = calendar.getTime();
            userDao.save_user_token_by_id(info.getUser_id(), uuid);
            userDao.save_user_token_die_time_by_id(info.getUser_id(), date);
            // 返回 token
            return uuid;
        } else {
            return "302";     // 账户或密码错误
        }

    }

    @Override
    public User get_user_by_mail(String user_email) {
        return userDao.get_user_by_mail(user_email);
    }

    @Override
    public User get_user_by_id(int id) {
        return userDao.get_user_by_id(id);
    }

    @Override
    public UserInfo get_userinfo_by_id(int id) {
        return userDao.get_userinfo_by_id(id);
    }

}
