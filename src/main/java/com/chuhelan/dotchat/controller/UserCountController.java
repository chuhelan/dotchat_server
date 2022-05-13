package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.beans.UserCount;
import com.chuhelan.dotchat.model.BaseInfo;
import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.model.UserCountInfo;
import com.chuhelan.dotchat.service.UserCountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 15:45
 */

@RestController
public class UserCountController {

    Gson gson = new Gson();

    @Autowired
    UserCountService userCountService;

    //根据id展示个人信息下的计数信息 post_count followers_count follows_count
    @GetMapping("show_user_count")
    public String get_user_count_by_id(int user_id) {
        UserCount userCount = userCountService.get_user_count_by_id(user_id);
        UserCountInfo userCountInfo = new UserCountInfo(userCount.getUser_id(), userCount.getUser_post_count(), userCount.getUser_followers_count(), userCount.getUser_follows_count());
        return gson.toJson(userCountInfo);

    }


    //    发了一条帖子 发推数量+1 user_post_count ++
    @GetMapping("post_plus")
    public String post_plus_by_id(int user_id) {
        int code = userCountService.user_post_count_plus_1(user_id);
        switch (code) {
            case 200:
                return gson.toJson(new BaseMessage(200, "帖子+1"));
            default:
                return gson.toJson(new BaseMessage(500, "未知错误！"));
        }
    }

    //    帖子--
//    发了一条帖子 发推数量+1 user_post_count ++
    @GetMapping("post_minus")
    public String post_minus_by_id(int user_id) {
        int code = userCountService.user_post_count_minus_1(user_id);
        switch (code) {
            case 200:
                return gson.toJson(new BaseMessage(200, "帖子-1"));
            default:
                return gson.toJson(new BaseMessage(500, "未知错误！"));
        }
    }

//    关注了一个人 自己关注列表+1 关注的人 粉丝列表+1

//

}
