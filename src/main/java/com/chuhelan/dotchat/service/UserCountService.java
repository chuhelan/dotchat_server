package com.chuhelan.dotchat.service;

import com.chuhelan.dotchat.beans.UserCount;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 15:49
 */

public interface UserCountService {

    int user_post_count_plus_1(int user_id);

    int user_post_count_minus_1(int user_id);

//    初始化用户计数表
    int add_user_by_id(int user_id);

    UserCount get_user_count_by_id(int user_id);


}
