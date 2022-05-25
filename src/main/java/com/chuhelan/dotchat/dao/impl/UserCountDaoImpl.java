package com.chuhelan.dotchat.dao.impl;

import com.chuhelan.dotchat.beans.UserCount;
import com.chuhelan.dotchat.dao.UserCountDao;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 15:58
 */
public class UserCountDaoImpl implements UserCountDao {

    @Override
    public int user_post_count_plus_1(int user_id) {
        return 0;
    }

    @Override
    public int user_post_count_minus_1(int user_id) {
        return 0;
    }

    @Override
    public int add_user_by_id(int user_id) {
        return 0;
    }

    @Override
    public UserCount get_user_count_by_id(int user_id) {
        return null;
    }

    @Override
    public int user_follows_count_minus_1(int mine_id) {
        return 0;
    }

    @Override
    public int user_followers_count_minus_1(int its_id) {
        return 0;
    }
}
