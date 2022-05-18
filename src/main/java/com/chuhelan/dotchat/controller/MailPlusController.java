package com.chuhelan.dotchat.controller;

import com.chuhelan.dotchat.model.BaseMessage;
import com.chuhelan.dotchat.service.MailPlusService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/15 20:27
 */

@RestController
public class MailPlusController {

    Gson gson = new Gson();

    @Autowired
    MailPlusService mailPlusService;

    @GetMapping("/send_mail")
    public String insert_mail_of_mail_address(String mail_address) {
        mailPlusService.insert_mail_of_mail_address(mail_address);
        return gson.toJson(new BaseMessage(200, "发送成功！"));
    }


}
