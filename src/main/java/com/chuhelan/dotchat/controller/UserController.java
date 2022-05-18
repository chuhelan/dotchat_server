package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.beans.UserInfo;
import com.chuhelan.dotchat.model.*;
import com.chuhelan.dotchat.service.UserService;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 20:54
 */

@RestController
public class UserController {

    Gson gson = new Gson();

    @Autowired
    UserService userService;

    /*使用手机端注册的时候不需要太多的信息,其余的自动生成*/
//    @GetMapping("/register")
//    public String register_user_by_userphone_and_username(User user) {
//        int code = userService.register_user_by_userphone_and_username(user);
//        switch (code) {
//            case 302:
//                return gson.toJson(new BaseMessage(302, "账号已存在!"));
//            case 200:
//                return gson.toJson(new BaseMessage(200, "注册成功！"));
//        }
//        return gson.toJson(new BaseMessage(500, "未知错误！"));
//    }

    @GetMapping("/register")
    public String register_from_website(User user) {
        int code = userService.register_from_website(user);
        switch (code) {
            case 302:
                return gson.toJson(new BaseMessage(302, "账号已存在!"));
            case 200:
                return gson.toJson(new BaseMessage(200, "注册成功！"));
        }

        return gson.toJson(new BaseMessage(500, "未知错误！"));
    }

    //  更新用户资料

    //  更新用户密码
    @GetMapping("/update_password")
    public String update_password(String user_password, int user_id) {
        String code = userService.update_password(user_password, user_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "密码修改成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "未知错误！"));
        }
    }

    /*
     *      根据image.chuhelan.com的api返回的图片链接更新至服务器
     *      然后再用id和url存到user_info表里面
     * */
    @GetMapping("/update")
    public String update_userinfo(int user_id, String user_name, String user_gender, String user_profile, String user_location) {
        String code = userService.update_userinfo(user_id, user_name, user_gender, user_profile, user_location);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "用户信息更新成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "未知错误！"));
        }
    }


    @GetMapping("/login")
    public String login_user_by_mail(ModelAndView modelAndView, String user_email, String user_password) {
        String back = "未知错误";
        int code = 500;
        // 验证登录
        String login_statue = userService.login_user_by_mail(user_email, user_password);
        switch (login_statue) {
            case "404":
                code = Integer.parseInt(login_statue);
                back = "账号不存在";
                break;
            case "302":
                code = Integer.parseInt(login_statue);
                back = "账号或密码错误";
                break;
            default:
                if (login_statue.length() > 3) {
                    code = 200;
                    back = login_statue + "|" + userService.get_user_by_mail(user_email).getUser_id();
                }
        }
        return gson.toJson(new BaseMessage(code, back));
    }

    @PostMapping("/verify")
    public String verify_user_by_id(int user_id, String token) {
        System.out.println("验证用户登录id：" + user_id + "token:" + token);
        boolean is_pass = userService.verify_token_by_id(user_id, token);
        if (is_pass) {
            return gson.toJson(new BaseMessage(200, "通过验证！"));
        } else {
            return gson.toJson(new BaseMessage(302, "验证失败！"));
        }
    }

    /*
     * 获取用户信息（用户名，性别，头像，位置，信誉分，认证状态，用户状态）
     * */
    @GetMapping("/info")
    public String get_user_info(int id) {
        UserInfo info = userService.get_userinfo_by_id(id);
        BaseInfo baseInfo = new BaseInfo(info.getUser_name(), info.getUser_gender(), info.getUser_profile(), info.getUser_location(), info.getUser_point(), info.getUser_authentication(), info.getUser_state());
        return gson.toJson(baseInfo);
    }

    @GetMapping("/info/{id}")
    public String get_user_info_id(@PathVariable int id) {
        UserInfo info = userService.get_userinfo_by_id(id);
        BaseInfo baseInfo = new BaseInfo(info.getUser_name(), info.getUser_gender(), info.getUser_profile(), info.getUser_location(), info.getUser_point(), info.getUser_authentication(), info.getUser_state());
        return gson.toJson(baseInfo);
    }

    @GetMapping("/basic")
    public String get_user_basic_info(int id) {
        User user = userService.get_user_by_id(id);
        Base base = new Base(user.getUser_email(), user.getUser_phonenumber());
        return gson.toJson(base);
    }

    @GetMapping("/basic/{id}")
    public String get_user_basic_info_by_id(@PathVariable int id) {
        User user = userService.get_user_by_id(id);
        Base base = new Base(user.getUser_email(), user.getUser_phonenumber());
        return gson.toJson(base);
    }

    //    通过user_name找user_id
    @GetMapping("/find_id")
    public String select_user_id_by_username(String user_name) {
        int user_id = userService.select_user_by_username(user_name);
        BaseId baseId = new BaseId(user_name, user_id);
        return gson.toJson(baseId);
    }

    //查找3个最新最新注册的用户 按倒序排列 limit 3
    @GetMapping("/latest_userinfo")
    public String select_latest_userinfo_not_in_cookie_userid(int user_id) {
        UserInfo[] userInfo = userService.select_latest_userinfo_not_in_cookie_userid(user_id);
        return gson.toJson(userInfo);
    }

    /* 关注一个人 follow表中添加关注的人id 然后在count表中follows_count关注的人表+1
    被关注者表count中followers_count+1粉丝数量+1*/
    @GetMapping("/follow")
    public String do_follow_and_count_plus(int user_id, int follow_id) {
        userService.do_follow_and_count_plus(user_id, follow_id);
        return gson.toJson(new BaseMessage(200, "添加成功！"));
    }

    //获取所有关注的人
    @GetMapping("/get_follows")
    public String select_all_follows(int user_id) {
        UserInfo[] userInfo = userService.select_all_follows(user_id);
        return gson.toJson(userInfo);
    }

    //获取自己所有的粉丝
    @GetMapping("/get_followers")
    public String select_all_followers(int user_id) {
        UserInfo[] userInfo = userService.select_all_followers(user_id);
        return gson.toJson(userInfo);
    }

    //用id查找该id所属的邮箱地址和昵称用于发送邮件服务
    @GetMapping("/email_name")
    public String select_user_email_and_user_name_by_userid(int user_id) {
        User user = userService.select_user_email_and_user_name_by_userid(user_id);
        MailBack mailBack = new MailBack(user.getUser_email(), user.getUser_name());
        return gson.toJson(mailBack);
    }

}

