package cn.wengzi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author leizige
 * 2020/01/15 23:24
 * desc:JavaMail config
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfiguration {

    @Autowired
    private MailProperties mailProperties;

    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    @Bean(name = "mailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setProtocol(mailProperties.getProtocol());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setProtocol(mailProperties.getProtocol());

        //以下代码绕过25端口使用 使用465端口发送邮件 因为阿里云Ecs服务器默认管理25端口,会导致邮件发送失败
        Properties props = new Properties();
//        //邮箱的发送服务器地址
        props.setProperty("mail.smtp.host", mailProperties.getHost());
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        //邮箱发送服务器端口,这里设置为465端口
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", mailProperties.getPort() + "");
        props.put("mail.smtp.auth", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
