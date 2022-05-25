package com.chuhelan.dotchat.service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:10
 */
public interface LikeService {
    String like_post_from_post_id(int user_id, int post_id);

    String dislike_post_from_post_id(int user_id, int post_id);

    String is_liked_from_user_id_and_post_id(int user_id,int post_id);
}
