package cn.wengzi.handler;

import cn.wengzi.model.MailEntity;
import cn.wengzi.util.MailTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "mail")
public class MailController {

    @Autowired
    private MailTools mailTools;

    @Value("${mail.sendToMails}")
    private String[] sendToMails;

    @GetMapping(value = "/simple")
    public void sendSimpleMail() {
        MailEntity mailEntity = MailEntity.builder()
                .subject("标题").content("内容").toEmails(sendToMails).build();
        mailTools.sendSimpleMail(mailEntity);
    }
}
