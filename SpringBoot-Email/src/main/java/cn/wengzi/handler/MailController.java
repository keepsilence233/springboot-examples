package cn.wengzi.handler;

import cn.wengzi.model.MailDto;
import cn.wengzi.util.MailTools;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "mail")
public class MailController {

    @Autowired
    private MailTools mailTools;


    @PostMapping(value = "/simple")
    public void sendSimpleMail(@ApiParam(value = "邮件内容", required = true) @Valid @RequestBody MailDto mailDto) {
        mailTools.sendSimpleMail(mailDto);
    }
}
