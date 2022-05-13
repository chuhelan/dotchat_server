package com.chuhelan.dotchat.dao;

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

    PostInfo[] show_post_from_post_author(int post_author);
}
