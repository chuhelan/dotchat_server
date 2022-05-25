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

    //取消关注 关注者count-1 被取消的人 粉丝数量-1
//    follows_count 关注的人数量
    int user_follows_count_minus_1(int mine_id);
//    followers_count 粉丝数量
    int user_followers_count_minus_1(int its_id);
}
