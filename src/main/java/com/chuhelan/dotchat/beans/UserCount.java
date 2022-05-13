package com.chuhelan.dotchat.beans;

import lombok.Data;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 14:33
 */

@Data
public class UserCount {
    private int user_id;
    private int user_post_count;
    private int user_followers_count;
    private int user_follows_count;
}
