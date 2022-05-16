package cn.wengzi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

/**
 * @author leizige
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailEntity {


//    @Email
    @Builder.Default
    private String fromEmail = "qx_leizige@163.com";

//    @Email
    @Builder.Default
    private String toEmail = "lei.tl@bitsun-inc.com";

    /**
     * 收件邮箱
     */
    @Builder.Default
    private String[] toEmails = new String[]{"lei.tl@bitsun-inc.com"};

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件附件
     */
    private List<File> file;

}
