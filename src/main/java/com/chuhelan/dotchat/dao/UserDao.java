package com.chuhelan.dotchat.dao;

import com.chuhelan.dotchat.beans.User;
import com.chuhelan.dotchat.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 17:34
 */

@Mapper
@Repository
public interface UserDao {
    int register_user_by_userphone_and_username(User u);

    int register_from_website(User u);

    User select_user_by_email_and_password(User u);

    User get_user_by_mail(String user_email);


    User get_user_by_id(int id);


    //    设置用户的token
    int save_user_token_by_id(int id, String token);

    //    设置用户toekn过期时间
    int save_user_token_die_time_by_id(int id, Date date);

    //
    int set_userinfo_by_userid(int id);
}
