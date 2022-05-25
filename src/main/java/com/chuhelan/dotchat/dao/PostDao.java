package com.chuhelan.dotchat.dao;

import com.chuhelan.dotchat.beans.Comment;
import com.chuhelan.dotchat.beans.PostInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:42
 */

@Mapper
@Repository
public interface PostDao {
    int insert_post(int post_author, String post_content, String post_image_url);

    PostInfo[] show_post_from_post_author(int post_author, int start);

    PostInfo[] show_all_post(int start);

    PostInfo show_post(int post_id);

    int insert_comment(int user_id, int post_id, String context);

    int add_comment_count(int post_id);

    Comment[] get_comment(int post_id);

    Comment get_fist_comment(int post_id);

    //帖子下方操作喜欢数量
    int post_like_count_plus_one(int post_id);

    int post_like_count_minus_one(int post_id);

    /*删除帖子 同时 自己信息页面 帖子数量-1 删除所有该post_id下面的评论*/
    int delete_post_by_post_id(int post_id);

    int delete_comment_by_post_id_if_exists(int post_id);

    int update_user_count_by_post_author_minus_one(int post_id);

    //删除评论
    int find_post_author_id_by_comment_id(int comment_id);

    int find_comment_author_id_by_comment_id(int comment_id);

    int delete_comment_by_comment_id(int comment_id);

    int update_comment_count_minus_one(int comment_id);



}
