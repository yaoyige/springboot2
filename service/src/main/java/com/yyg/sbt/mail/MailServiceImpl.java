package com.yyg.sbt.mail;

import com.yyg.sbt.service.MailService;
import com.yyg.sbt.store.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

   /* @Value(value = "${email.host}")
    public String host;
    @Value(value = "${email.port}")
    public String port;
    @Value(value = "${email.username}")
    public String username;
    @Value(value = "${email.password}")
    public String password;
    @Value(value = "${email.form}")
    public String sendForm;*/

    @Autowired
    public Mail mail;

    /**
     * 个人邮箱发送
     * @param someone
     * @param subject
     * @param content
     */
    public void sendPersonEmail(String someone, String subject, String content) {
       /* Properties props = new Properties();
        //协议
        props.setProperty("mail.transport.protocol", "smtp");
        //服务器
        props.setProperty("mail.smtp.host", host);
        //端口
        props.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        props.setProperty("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
*/
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", "SMTP");
        //服务器
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        //端口
        prop.setProperty("mail.smtp.port", "465");
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");

        prop.setProperty("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        prop.setProperty("mail.debug", "true");

        //获取Session对象
        Session s = Session.getDefaultInstance(prop, new Authenticator() {
            //此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication pa = new PasswordAuthentication("username", "password");
                return pa;
            }
        });
        //设置session的调试模式，发布时取消
        s.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(s);
        try {
            //发件人邮箱
            mimeMessage.setFrom(new InternetAddress("yyg0429@163.com"));
            //收件人邮箱
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("chao521it@163.com"));
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