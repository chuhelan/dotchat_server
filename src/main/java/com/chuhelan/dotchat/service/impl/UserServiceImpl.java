package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.User;
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

    @Override
    public int register_user_by_userphone_and_username(User u) {
        userDao.register_user_by_userphone_and_username(u);
        return 200;
    }

    @Override
    public int register_from_website(User u) {
        userDao.register_from_website(u);
        return 200;
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
    public String login_user_by_mail(String user_email, String user_password) {
        // 获取用户信息
        User info = userDao.get_user_by_mail(user_email);
        if(info == null) return "404";        // 账户或密码不存在
        // 验证用户密码
        if(info.getUser_password().equals(user_password)) {
            // 创建 token
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            // 写入数据库
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,5);
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


}
