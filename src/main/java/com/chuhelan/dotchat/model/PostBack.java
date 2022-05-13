package com.chuhelan.dotchat.model;

import com.chuhelan.dotchat.beans.PostInfo;

import java.util.Date;
import java.util.List;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/10 18:51
 */
public class PostBack {

    int post_id;
    int post_author;
    Date post_date;
    String post_content;
    String post_status;
    String comment_status;
    String post_url;
    String post_image_url;
    int post_comment_count;
    int post_views_count;
    int post_likes_count;


    public PostBack(int post_id, int post_author, Date post_date, String post_content, String post_status, String comment_status, String post_url, String post_image_url, int post_comment_count, int post_views_count, int post_likes_count) {
        this.post_id = post_id;
        this.post_author = post_author;
        this.post_date = post_date;
        this.post_content = post_content;
        this.post_status = post_status;
        this.comment_status = comment_status;
        this.post_url = post_url;
        this.post_image_url = post_image_url;
        this.post_comment_count = post_comment_count;
        this.post_views_count = post_views_count;
        this.post_likes_count = post_likes_count;
    }

}
