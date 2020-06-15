package cn.wengzi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author leizige
 * desc:邮件信息实体
 */
@Data
@ApiModel
public class MailDto {

    @Email
    @ApiModelProperty(value = "收件邮箱")
    private String toEmail;

    @ApiModelProperty(value = "邮件主题")
    private String subject;

    @ApiModelProperty(value = "邮件内容")
    private String content;

}
