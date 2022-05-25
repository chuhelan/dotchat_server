package com.chuhelan.dotchat.service;

import com.chuhelan.dotchat.beans.Comment;
import com.chuhelan.dotchat.beans.PostInfo;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:43
 */
public interface PostService {
    int post(int post_author, String post_content, String post_image_url);

    PostInfo[] show_post_from_post_author(int post_author, int start);

    PostInfo[] show_all_post(int start);

    PostInfo show_post(int post_id);

    // --------------------------

    int comment(int post_id, int user_id, String context);

    Comment[] get_comment(int post_id);

    Comment get_fist_comment(int post_id);

    String delete_post_by_post_id(int post_id);

    String delete_comment_by_comment_id(int user_id, int comment_id);

    String can_i_delete_this_comment(int user_id, int comment_id);
}
