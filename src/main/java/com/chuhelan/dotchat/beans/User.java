package com.chuhelan.dotchat.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 17:27
 */

//用户基本信息表 用户可以使用电子邮件地址和手机号码登录
@Data
public class User {
    private Integer user_id;
    private String user_name;
    private String user_email;
    private String user_phonenumber;
    private String user_password;
    private String user_session;
    private Date user_diesession_time;
}
