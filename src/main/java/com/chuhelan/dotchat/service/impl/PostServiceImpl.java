package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.beans.PostInfo;
import com.chuhelan.dotchat.dao.PostDao;
import com.chuhelan.dotchat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/9 21:44
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public int post(int post_author, String post_content, String post_image_url) {
        postDao.insert_post(post_author, post_content, post_image_url);
        return 200;
    }

    @Override
    public PostInfo[] show_post_from_post_author(int post_author) {
        return postDao.show_post_from_post_author(post_author);
    }
}
