package com.nbst.overall;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailUtil {
//	private static final String HOST = "smtp.qq.com";
//    private static final Integer PORT = 25;
//    private static final String USERNAME = "541653057@qq.com";
//    private static final String PASSWORD = "fnimqyrbmfmgbcbc";
//    private static final String EMAILFORM = "541653057@qq.com";
    private static JavaMailSenderImpl mailSender = createMailSender();
    private static Session session = createSession();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(MailConfiguration.getHost());
        sender.setPort(MailConfiguration.getPort());
        sender.setUsername(MailConfiguration.getUsername());
        sender.setPassword(MailConfiguration.getPassword());
        sender.setDefaultEncoding(MailConfiguration.getEncoding());
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", MailConfiguration.getTimeout() + "");
        p.setProperty("mail.smtp.auth", MailConfiguration.getAuth() + "");
        p.setProperty("mail.smtp.starttls.enable", MailConfiguration.getStarttlsEnable() + "");
        sender.setJavaMailProperties(p);
        return sender;
    }
    /**
     * 创建SMTP 465端口连接
     * @return
     */
    private static Session createSession() {
        Properties properties = new Properties();  
        InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            log.error("{}", e);
        }
        
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfiguration.getUsername(), MailConfiguration.getPassword());
            }
        });
        return session;
    }
    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendHtmlMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,MailConfiguration.getEncoding());
        messageHelper.setFrom(MailConfiguration.getFrom(), "系统名称");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }
    
    /**
     * 通过465号端口发送邮件，因为25号端口被阿里云屏蔽掉了
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws IOException 
     */
    public static void sendSSLHtmlMail(String to, String subject, String html) throws MessagingException,IOException {
        MimeMessage msg = new MimeMessage(session);
        MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, MailConfiguration.getEncoding()); 
        messageHelper.setFrom(MailConfiguration.getFrom(), "系统名称");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        Transport.send(msg);
    }

}
