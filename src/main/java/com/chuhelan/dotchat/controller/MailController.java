package com.chuhelan.dotchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/13 20:54
 */


@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender; //框架自带的

    @Value("${spring.mail.username}")  //发送人的邮箱
    private String from;


    /**
     * 普通文本邮件发送
     * @param to 发给谁  对方邮箱
     * @param subject 标题  测试邮件
     * @param content 内容
     * @return
     */
    @RequestMapping("/sendTextMail")
    public String sendTextMail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);//发送
            System.out.println("测试邮件已发送。");
        } catch (Exception e) {
            System.out.println("发送邮件时发生异常了！");
            return "邮件发送失败："+e;
        }
        return "邮件发送成功";
    }

    /**
     * 发送HTML邮件
     * @param to 发给谁  对方邮箱
     * @param subject 标题  测试邮件
     * @param content 内容
     * @return
     */
    @RequestMapping("/sendHtmlMail")
    public String sendHtmlMail(String to,String subject,String content){
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);//发送HTML内容时需要设置html开启,默认为false
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败："+e;
        }
        return "邮件发送成功";
    }


    /**
     * 发送附件邮件
     * @param to 发给谁  对方邮箱
     * @param subject 标题  测试邮件
     * @param content 内容
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping("/sendAttachmentMail")
    public String sendAttachmentMail(String to,String subject,String content,String filePath){
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);//需要开启邮件附件
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            FileSystemResource fileSystemResource=new FileSystemResource(new File(filePath));
            String fileName=fileSystemResource.getFilename();
            mimeMessageHelper.addAttachment(fileName,fileSystemResource);//添加附件
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "邮件发送失败："+e;
        }
        return "邮件发送成功";
    }

}
