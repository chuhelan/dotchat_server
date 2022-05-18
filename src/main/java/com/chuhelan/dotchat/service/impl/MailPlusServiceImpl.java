package com.chuhelan.dotchat.service.impl;

import com.chuhelan.dotchat.dao.MailPlusDao;
import com.chuhelan.dotchat.service.MailPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/15 20:41
 */
@Service
public class MailPlusServiceImpl implements MailPlusService {
    @Autowired
    MailPlusDao mailPlusDao;

    @Override
    public int insert_mail_of_mail_address(String mail_address) {
        return mailPlusDao.insert_mail_of_mail_address(mail_address);
    }


}
