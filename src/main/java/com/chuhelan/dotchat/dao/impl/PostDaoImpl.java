package com.chuhelan.dotchat.dao.impl;

import com.chuhelan.dotchat.beans.Comment;
import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.dao.PostDao;

import java.util.List;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:42
 */
public class PostDaoImpl implements PostDao {
    @Override
    public int insert_post(int post_author, String post_content, String post_image_url) {
        return 0;
    }

    @Override
    public PostInfo[] show_post_from_post_author(int post_author, int start) {
        return new PostInfo[0];
    }

    @Override
    public PostInfo[] show_all_post(int start) {
        return new PostInfo[0];
    }

    @Override
    public PostInfo show_post(int post_id) {
        return null;
    }

    // -------------------------------

    @Override
    public int insert_comment(int user_id, int post_id, String context) {
        return 0;
    }

    @Override
    public int add_comment_count(int post_id) {
        return 0;
    }

    @Override
    public Comment[] get_comment(int post_id) {
        return new Comment[0];
    }

    @Override
    public Comment get_fist_comment(int post_id) {
        return null;
    }

    @Override
    public int post_like_count_plus_one(int post_id) {
        return 0;
    }

    @Override
    public int post_like_count_minus_one(int post_id) {
        return 0;
    }

    @Override
    public int delete_post_by_post_id(int post_id) {
        return 0;
    }

    @Override
    public int delete_comment_by_post_id_if_exists(int post_id) {
        return 0;
    }

    @Override
    public int update_user_count_by_post_author_minus_one(int post_id) {
        return 0;
    }

    @Override
    public int find_post_author_id_by_comment_id(int comment_id) {
        return 0;
    }

    @Override
    public int find_comment_author_id_by_comment_id(int comment_id) {
        return 0;
    }


    @Override
    public int delete_comment_by_comment_id(int comment_id) {
        return 0;
    }

    @Override
    public int update_comment_count_minus_one(int comment_id) {
        return 0;
    }
}
