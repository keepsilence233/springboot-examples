package cn.wengzi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @author leizige
 */
@Data
@Validated
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_user")
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = -7715320745921659416L;

    @TableField(value = "id")
    private Integer id;
    @TableField(value = "user_name")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "name")
    private String name;
    @TableField(value = "email")
    private String email;
    @TableField(value = "age")
    private Integer age;
}
