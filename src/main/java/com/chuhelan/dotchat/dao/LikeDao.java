package com.chuhelan.dotchat.dao;

import com.chuhelan.dotchat.beans.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:09
 */

@Mapper
@Repository
public interface LikeDao {
    int like_post_from_post_id(int user_id, int post_id);

    int dislike_post_from_post_id(int user_id, int post_id);

    Like is_liked_from_user_id_and_post_id(int user_id, int post_id);

}
