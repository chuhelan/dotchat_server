package com.chuhelan.dotchat.beans;

import lombok.Data;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/23 15:08
 */

@Data
public class Like {
    private int like_id;
    private int like_user_id;
    private String like_user_name;
    private int like_parent;
}
