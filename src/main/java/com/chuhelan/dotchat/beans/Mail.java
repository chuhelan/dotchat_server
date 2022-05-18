package com.chuhelan.dotchat.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/15 20:15
 */

@Data
public class Mail {
    private String mail_address;
    private Date mail_clicktime;
}
