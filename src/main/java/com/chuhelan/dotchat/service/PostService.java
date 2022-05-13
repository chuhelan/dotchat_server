package com.chuhelan.dotchat.service;

import com.chuhelan.dotchat.beans.PostInfo;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:43
 */
public interface PostService {
    int post(int post_author, String post_content, String post_image_url);

    PostInfo[] show_post_from_post_author(int post_author);

}
