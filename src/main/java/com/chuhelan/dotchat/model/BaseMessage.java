package com.chuhelan.dotchat.model;

import lombok.Data;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/4/14 21:09
 */
@Data
public class BaseMessage {
    int code;
    String message;

    public BaseMessage(int i, String s) {
        code = i;
        message = s;
    }
}
