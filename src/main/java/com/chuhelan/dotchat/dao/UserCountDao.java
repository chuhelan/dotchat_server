package com.chuhelan.dotchat.dao;

import com.chuhelan.dotchat.beans.UserCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/11 15:58
 */

@Mapper
@Repository
public interface UserCountDao {

    int user_post_count_plus_1(int user_id);

    int user_post_count_minus_1(int user_id);

    int add_user_by_id(int user_id);

    UserCount get_user_count_by_id(int user_id);

}
