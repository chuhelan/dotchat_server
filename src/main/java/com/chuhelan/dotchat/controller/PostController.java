package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.model.PostBack;
import com.chuhelan.dotchat.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:34
 */

@RestController
public class PostController {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

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
    @GetMapping("/show_post/{page}")
    public String show_post_from_post_author(@PathVariable int page, Optional<Integer> post_author) {
        PostInfo[] postInfo = new PostInfo[0];
        if (post_author.isPresent()) {
            // 如果传递了发送者 ID 就获取 ID 的
            postInfo = postService.show_post_from_post_author(post_author.get(), page * 5);
        } else {
            // 如果没有，则获取所有的有效推文
            postInfo = postService.show_all_post(page * 10);
        }
        System.out.println("=======================");
        for (PostInfo info : postInfo) {
            System.out.println(info);
        }
        System.out.println("=======================");
        return gson.toJson(postInfo);
    }

    /* 获取单条文章内容 */
    @GetMapping("/get_post/{post_id}")
    public String getPost(@PathVariable int post_id) {
        return gson.toJson(postService.show_post(post_id));
    }

    // ----------------------------------

    /*
        发送评论

        参数：评论对应的文章 ID，评论者，评论的正文
    */
    @GetMapping("/comment")
    public String comment(int post_id, int user_id, String comment) {
        int code = postService.comment(post_id, user_id, comment);
        switch (code) {
            case 200:
                return gson.toJson(new BaseMessage(200, "发送成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "服务异常！"));
        }
    }

    /* 获取指定文章的全部评论 */
    @GetMapping("/get_comment/{post_id}")
    public String getComment(@PathVariable int post_id) {
        return gson.toJson(postService.get_comment(post_id));
    }

    @GetMapping("/get_fist_comment/{post_id}")
    public String getFistComment(@PathVariable int post_id) {
        return gson.toJson(postService.get_fist_comment(post_id));
    }

    /*删除帖子 同时 自己信息页面 帖子数量-1 删除所有该post_id下面的评论*/
    @GetMapping("/delete_post")
    public String delete_post_by_post_id(int post_id) {
        String code = postService.delete_post_by_post_id(post_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "删除成功！"));
            default:
                return gson.toJson(new BaseMessage(500, "我也不知道啥错误！"));
        }
    }

    /*删除评论*/
    @GetMapping("/delete_comment")
    public String delete_comment_by_comment_id(int user_id, int comment_id) {
        String code = postService.delete_comment_by_comment_id(user_id, comment_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "删除成功"));
            case "403":
                return gson.toJson(new BaseMessage(403, "删除失败，没有权限"));
            default:
                return gson.toJson(new BaseMessage(500, "我也不知道啥错误！"));
        }
    }

    /* 通过user_id 和 comment_id 判断该用户是否能删除该评论 */
    @GetMapping("/can_i_delete")
    public String can_i_delete_this_comment(int user_id, int comment_id) {
        String code = postService.can_i_delete_this_comment(user_id, comment_id);
        switch (code) {
            case "200":
                return gson.toJson(new BaseMessage(200, "true"));
            case "403":
                return gson.toJson(new BaseMessage(403, "false"));
            default:
                return gson.toJson(new BaseMessage(500, "error"));
        }
    }
}
