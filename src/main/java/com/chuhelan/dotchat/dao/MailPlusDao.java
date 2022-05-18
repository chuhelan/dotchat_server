package com.chuhelan.dotchat.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/15 20:39
 */

@Mapper
@Repository
public interface MailPlusDao {
    int insert_mail_of_mail_address(String mail_address);
}
