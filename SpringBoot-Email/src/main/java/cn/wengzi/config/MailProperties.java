package cn.wengzi.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author leizige
 * 2020/01/15 23:21
 * desc:mail 属性配置文件读取
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    @ApiModelProperty(value = "邮箱主机地址")
    private String host;

    @ApiModelProperty(value = "端口,163默认 25")
    private int port;

    @ApiModelProperty(value = "邮箱协议")
    private String protocol;

    @ApiModelProperty(value = "邮箱账号")
    private String username;

    @ApiModelProperty(value = "邮箱授权码")
    private String password;

}
