package cn.wengzi.util;

import cn.wengzi.config.MailProperties;
import cn.wengzi.model.MailDto;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * @author leizige
 * desc:邮件工具类
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class MailTools {

    @Resource
    @Qualifier(value = "mailSender")
    private JavaMailSenderImpl mailSender;

    @Autowired
    private MailProperties mailProperties;

    /**
     * SimpleMailMessage 发送简单邮件
     *
     * @param mailDto 邮件内容
     */
    public void sendSimpleMail(MailDto mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setTo(mailDto.getToEmail());
        mailMessage.setSubject(mailDto.getSubject());
        mailMessage.setText(mailDto.getContent());
        mailMessage.setSentDate(new Date());
        log.info(mailMessage.toString());
        try {
            mailSender.send(mailMessage);
            log.info("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            log.error("邮件发送失败", e);
        }
    }

    /**
     * MimeMessageHelper:发送带附件的邮件
     *
     * @param mailDto 邮件内容
     */
    private void sendAttachmentsMail(MailDto mailDto) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mailDto.getToEmail());
            helper.setSubject(mailDto.getSubject());
            helper.setText(mailDto.getContent());

            FileSystemResource file = new FileSystemResource(new File("附件名称"));
            helper.addAttachment("附件-1.jpg", file);
            helper.addAttachment("附件-2.jpg", file);

            mailSender.send(mimeMessage);

            log.info("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件发送失败", e);
        }
    }

    /**
     * MimeMessageHelper:嵌入静态资源邮件
     *
     * @param mailDto 邮件内容
     */
    public void sendInlineMail(MailDto mailDto) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mailDto.getToEmail());
            helper.setSubject(mailDto.getSubject());
            // 静态资源 邮件内容(支持HTML标签)： "<html><body><img src=\"cid:xxx\" ></body></html>", true
            helper.setText(mailDto.getContent());
            //TODO 这里要注意的是 addInline 中资源名称 xxx 要和 正文中的 cid:xxx 对应起来
            FileSystemResource file = new FileSystemResource(new File("xxx.jpg"));
            helper.addInline("xxx", file);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件发送异常", e);
        }
        try {
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            log.info("邮件发送失败", e);
        }
    }


    /**
     * MimeMessageHelper: 发送模板邮件
     * <story>
     *     在resources/templates/下，创建一个模板页面template.vm
     *     SpringBoot自动化配置该目录下的模板文件
     * </story>
     *
     * @param mailDto 邮件内容
     */
    public void sendTemplateMail(MailDto mailDto) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mailDto.getToEmail());
            helper.setSubject(mailDto.getSubject());

            Map<String, Object> model = Maps.newHashMapWithExpectedSize(1);
//            model.put("username", "didi");
//            String text = VelocityEngineUtils.mergeTemplateIntoString(
//                    velocityEngine, "template.vm", "UTF-8", model);
//            helper.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        mailSender.send(mimeMessage);
    }
}
