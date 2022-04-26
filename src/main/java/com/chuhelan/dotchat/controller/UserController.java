package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/login")
    public String login_user_by_mail(String user_email, String user_password) {
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

}

