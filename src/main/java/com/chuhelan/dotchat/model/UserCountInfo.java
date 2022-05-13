package com.chuhelan.dotchat.model;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 20:32
 */
public class UserCountInfo {
    int user_id;
    int user_post_count;
    int user_followers_count;
    int user_follows_count;

    public UserCountInfo(int user_id, int user_post_count, int user_followers_count, int user_follows_count) {
        this.user_id = user_id;
        this.user_post_count = user_post_count;
        this.user_followers_count = user_followers_count;
        this.user_follows_count = user_follows_count;
    }
}
