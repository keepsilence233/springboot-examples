package cn.wengzi.jsr303.entity;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author wengzi
 * @date 19/105 21:12
 * @Description Student entity
 * message:设置默认显示错误信息
 */
@Data
public class Student {
    @NotNull
    private Long id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码长度必须在6~18之内")
    private String password;
    @NotNull
    @Min(value = 1, message = "年龄最小只能为1岁")
    @Max(value = 110, message = "年龄最大只能为110岁")
    private Integer age;
    @NotNull
    @Past(message = "必须是一个过去的日期")
    private Date birthday;
    @NotNull
    @Email(message = "邮箱格式错误")
    private String email;
}
