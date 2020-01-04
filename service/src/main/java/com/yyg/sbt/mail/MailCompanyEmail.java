package com.yyg.sbt.mail;

import com.yyg.sbt.service.MailService;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 企业级邮箱
 */
public class MailCompanyEmail implements MailService {
    @Value(value = "${email.username}")
    public String username;
    @Value(value = "${email.password}")
    public String password;
    /**
     * 企业邮箱发送
     * @param someone 邮箱名称
     * @param subject 邮件标题
     * @param content 邮件内容
     */
    public void sendPersonEmail(String someone, String subject, String content) {
        Properties props = new Properties();
        //协议
        props.setProperty("mail.transport.protocol", "SMTP");
        //服务器
        props.setProperty("mail.smtp.host", "smtp.163.com");
        //端口
        props.setProperty("mail.smtp.port", "465");

        props.setProperty("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        props.setProperty("mail.debug", "true");

        //获取Session对象
        Session s = Session.getDefaultInstance(props, new Authenticator() {
            //此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication pa = new PasswordAuthentication(username, password);
                return pa;
            }
        });
        //设置session的调试模式，发布时取消
        s.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(s);
        try {
            //发件人邮箱    v-yaoyige.ea@ea.bytedance.com
            mimeMessage.setFrom(new InternetAddress("企业级邮箱"));
            //收件人邮箱   v-yaoyige.ea@bytedance.com
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("收件人企业级邮箱"));
            //设置主题
            mimeMessage.setSubject("你是个啥人！！狗----------测试");
            mimeMessage.setSentDate(new Date());
            //设置内容
            mimeMessage.setText("你是个啥人！！狗----------测试-------内容");
            mimeMessage.saveChanges();
            //发送
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
