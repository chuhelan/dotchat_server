package com.chuhelan.dotchat.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:34
 */

@Data
public class PostInfo {
    private int post_id;
    private int post_author;
    private Date post_date;
    private String post_content;
    private String post_status;
    private String post_comment_status;
    private String post_url;
    private String post_image_url;
    private int post_comment_count;
    private int post_views_count;
    private int post_likes_count;
}
