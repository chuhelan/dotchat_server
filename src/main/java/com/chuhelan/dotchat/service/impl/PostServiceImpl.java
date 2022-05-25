package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.Comment;
import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.dao.PostDao;
import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:44
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public int post(int post_author, String post_content, String post_image_url) {
        postDao.insert_post(post_author, post_content, post_image_url);
        return 200;
    }

    @Override
    public PostInfo[] show_post_from_post_author(int post_author, int start) {
        return postDao.show_post_from_post_author(post_author, start);
    }

    @Override
    public PostInfo[] show_all_post(int start) {
        return postDao.show_all_post(start);
    }

    @Override
    public PostInfo show_post(int post_id) {
        return postDao.show_post(post_id);
    }

    // -----------------------------------------------------------------

    @Override
    public int comment(int post_id, int user_id, String context) {
        postDao.insert_comment(user_id, post_id, context);
        postDao.add_comment_count(post_id);
        return 200;
    }

    @Override
    public Comment[] get_comment(int post_id) {
        return postDao.get_comment(post_id);
    }

    @Override
    public Comment get_fist_comment(int post_id) {
        return postDao.get_fist_comment(post_id);
    }


    /*删除帖子 同时 自己信息页面 帖子数量-1 删除所有该post_id下面的评论*/
    @Override
    public String delete_post_by_post_id(int post_id) {
        postDao.update_user_count_by_post_author_minus_one(post_id);
        postDao.delete_post_by_post_id(post_id);
        postDao.delete_comment_by_post_id_if_exists(post_id);
        return "200";
    }

    @Override
    public String delete_comment_by_comment_id(int user_id, int comment_id) {
        if (user_id == postDao.find_post_author_id_by_comment_id(comment_id)) {
            postDao.update_comment_count_minus_one(comment_id);
            postDao.delete_comment_by_comment_id(comment_id);
            return "200";
        } else if (user_id == postDao.find_comment_author_id_by_comment_id(comment_id)) {
            postDao.update_comment_count_minus_one(comment_id);
            postDao.delete_comment_by_comment_id(comment_id);
            return "200";
        } else {
            return "403";
        }
    }

    @Override
    public String can_i_delete_this_comment(int user_id, int comment_id) {
        if (user_id == postDao.find_post_author_id_by_comment_id(comment_id)){
            return "200";
        }else if (user_id == postDao.find_comment_author_id_by_comment_id(comment_id)){
            return "200";
        }else {
            return "403";
        }
    }
}
