package com.limp.async;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.Future;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2018/12/30 13:23
 * @website： www.shinians.com
 */
@Service
public class SleepService {

    @Autowired
    private JavaMailSenderImpl mailSender;
    private  static final String  fromAddr="shiniandate@163.com";
    private  static final String  toAddr="907820813@qq.com";
    private  static final String  company="LP框架官网";


    /**
     *异步问候
     * @param name
     * @return
     */
    @Async
   public Future<String>   sayHallo(String name){
       try {
           //休眠10秒
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       return  new AsyncResult<String>("异步您好"+name);
   }

    /**
     *问候【非异步】
     * @param name
     * @return
     */
    public  String  sayHallo1(String name){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "非异步，您好-》"+name;
    }
    /**********************邮件测试*******************************/

    /**
     * 普通文本发送 【1】
     */
    public void sendSimpleMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[] {"907820813@qq.com"});
        simpleMailMessage.setFrom("shiniandate@163.com");
        simpleMailMessage.setSubject("普通文本");
        simpleMailMessage.setText("SpringBoot邮件发送内容页。");
        // 发送邮件
        mailSender.send(simpleMailMessage);
        System.out.println("邮件已发送");
    }
    /**
     * 发送包含HTML文本的邮件【2】
     * @throws Exception
     */
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(toAddr);
        mimeMessageHelper.setFrom(fromAddr,company);
        //设置标题
        mimeMessageHelper.setSubject("发送邮件测试【支持html】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>邮件测试Html</h1><p>这是一封html的邮件</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

    }

    /**
     *
     * @throws Exception
     */
//    @Async
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(toAddr);
        mimeMessageHelper.setFrom(fromAddr);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>这是一封带附件的邮件</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource img = new FileSystemResource(new File("D:/1.jpg"));
        mimeMessageHelper.addAttachment("image.jpg", img);
        // 发送邮件
        mailSender.send(mimeMessage);

    }
}
