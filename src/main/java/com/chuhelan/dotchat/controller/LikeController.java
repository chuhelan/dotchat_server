package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.service.LikeService;
import com.chuhelan.dotchat.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:08
 */

@RestController
public class LikeController {

    Gson gson = new Gson();

    @Autowired
    LikeService likeService;

    @GetMapping("/like_post")
    public String like_post_from_post_id(int user_id, int post_id) {
        String code = likeService.like_post_from_post_id(user_id, post_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "喜欢成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "我不知道发生了什么，请联系我！"));
        }
    }

    @GetMapping("/dislike_post")
    public String dislike_post_from_post_id(int user_id, int post_id) {
        String code = likeService.dislike_post_from_post_id(user_id, post_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "取消喜欢成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "我不知道发生了什么，请联系我！"));
        }
    }

    @GetMapping("/is_liked")
    public String is_liked_from_user_id_and_post_id(int user_id, int post_id) {
        String code = likeService.is_liked_from_user_id_and_post_id(user_id, post_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "true"));
            default:
                return gson.toJson(new BaseMessage(404, "false"));
        }
    }

}
