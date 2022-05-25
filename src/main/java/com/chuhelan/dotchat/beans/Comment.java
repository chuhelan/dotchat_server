package com.chuhelan.dotchat.beans;

import lombok.Data;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 11:40
 */
// 回复信息结构
@Data
public class Comment {
    private int comment_id;
    private int comment_post_id;
    private String comment_author_name;
    private String comment_author_profileurl;
    private String comment_content;
    private int comment_parent;
}
