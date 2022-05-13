package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.UserCount;
import com.chuhelan.dotchat.dao.UserCountDao;
import com.chuhelan.dotchat.service.UserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 15:50
 */

@Service
public class UserCountServiceImpl implements UserCountService {

    @Autowired
    UserCountDao userCountDao;

    @Override
    public int user_post_count_plus_1(int user_id) {
        userCountDao.user_post_count_plus_1(user_id);
        return 200;
    }

    //    minus
    @Override
    public int user_post_count_minus_1(int user_id) {
        userCountDao.user_post_count_minus_1(user_id);
        return 200;
    }

    @Override
    public int add_user_by_id(int user_id) {
        userCountDao.add_user_by_id(user_id);
        return 0;
    }

    @Override
    public UserCount get_user_count_by_id(int user_id) {
        return userCountDao.get_user_count_by_id(user_id);
    }
}
