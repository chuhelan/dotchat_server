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

    User get_user_by_phone(String user_phone);

    User get_user_by_id(int id);

    UserInfo get_userinfo_by_id(int id);

    int set_userinfo_by_id_and_name(int user_id, String user_name);


    //    设置用户的token
    int save_user_token_by_id(int id, String token);

    //    设置用户toekn过期时间
    int save_user_token_die_time_by_id(int id, Date date);

    //
    int set_userinfo_by_userid(int id);

    int update_password(String user_password, int user_id);

    int update_userinfo(int user_id, String user_name, String user_gender, String user_profile, String user_location);

    int update_username_by_id(int user_id, String user_name);

    int select_user_by_username(String user_name);

    UserInfo[] select_latest_userinfo_not_in_cookie_userid(int user_id);

    int do_follow_and_count_plus(int user_id, int follow_id);

    //    自己id下 关注者数量+1
    int do_user_id_count_follows_plus(int user_id);

    //    关注者id下 粉丝数量+1
    int do_follower_id_count_followers_plus(int follow_id);

    //    利用post查找user_info信息
    UserInfo[] select_all_follows(int user_id);

    UserInfo[] select_all_followers(int user_id);

    User select_user_email_and_user_name_by_userid(int user_id);

    int cancel_follow_by_user_id(int mine_id, int its_id);

    UserInfo[] search_user_name_by_key_words(String wd);

    UserInfo[] get_all_follows_by_user_id(int user_id);

    UserInfo[] get_all_followers_by_user_id(int user_id);
}
