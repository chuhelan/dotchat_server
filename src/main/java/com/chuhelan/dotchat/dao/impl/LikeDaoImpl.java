package com.chuhelan.dotchat.dao.impl;

import com.chuhelan.dotchat.beans.Like;
import com.chuhelan.dotchat.dao.LikeDao;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:09
 */
public class LikeDaoImpl implements LikeDao {


    @Override
    public int like_post_from_post_id(int user_id, int post_id) {
        return 0;
    }

    @Override
    public int dislike_post_from_post_id(int user_id, int post_id) {
        return 0;
    }

    @Override
    public Like is_liked_from_user_id_and_post_id(int user_id, int post_id) {
        return null;
    }
}
