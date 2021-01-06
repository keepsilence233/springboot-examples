package cn.wengzi.handler;

import cn.wengzi.model.MailEntity;
import cn.wengzi.util.MailTools;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
