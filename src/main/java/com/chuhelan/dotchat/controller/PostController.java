package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.model.PostBack;
import com.chuhelan.dotchat.service.PostService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:34
 */

@RestController
public class PostController {

    Gson gson = new Gson();

    @Autowired
    PostService postService;

    /*发送帖子

    需要传入 ： 帖子作者id 发送日期数据库写入的时候自动填入 帖子内容 帖子图像地址

     */
    @GetMapping("/post")
    public String post(int post_author, String post_content, String post_image_url) {
        int code = postService.post(post_author, post_content, post_image_url);
        switch (code) {
            case 200:
                return gson.toJson(new BaseMessage(200, "发送成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "服务异常！"));
        }
    }

    /*根据送发送者id查找所有的推文*/
    /*不需要认证*/
    @GetMapping("/show_post")
    public String show_post_from_post_author(int post_author) {
        PostInfo[] postInfo = postService.show_post_from_post_author(post_author);
        System.out.println("=======================");
        for (PostInfo info: postInfo) {
            System.out.println(info);
        }
        System.out.println("=======================");
        return gson.toJson(postInfo);
    }

}
