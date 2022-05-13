package com.chuhelan.dotchat.dao.impl;

import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.dao.PostDao;

import java.util.List;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:42
 */
public class PostDaoImpl implements PostDao {
    @Override
    public int insert_post(int post_author, String post_content, String post_image_url) {
        return 0;
    }

    @Override
    public PostInfo[] show_post_from_post_author(int post_author) {
        return new PostInfo[0];
    }
}
