package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.Like;
import com.chuhelan.dotchat.dao.LikeDao;
import com.chuhelan.dotchat.dao.PostDao;
import com.chuhelan.dotchat.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:10
 */

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeDao likeDao;

    @Autowired
    PostDao postDao;

    // 添加喜欢信息 帖子下方喜欢count+1
    @Override
    public String like_post_from_post_id(int user_id, int post_id) {
        likeDao.like_post_from_post_id(user_id, post_id);
        postDao.post_like_count_plus_one(post_id);
        return "200";
    }

    //取消喜欢信息 帖子下方喜欢-1
    @Override
    public String dislike_post_from_post_id(int user_id, int post_id) {
        likeDao.dislike_post_from_post_id(user_id, post_id);
        postDao.post_like_count_minus_one(post_id);
        return "200";
    }

    //查找post_like表下有没有该喜欢的内容信息 返回200 或者404
    @Override
    public String is_liked_from_user_id_and_post_id(int user_id, int post_id) {
        Like like = likeDao.is_liked_from_user_id_and_post_id(user_id, post_id);
        if (like != null) {
            return "200";
        } else {
            return "404";
        }


    }
}
