package cn.wengzi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wengzi
 * @date 19/10/5 17:58
 * @Description User entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 2741099921820557109L;
    private String name;
    private String password;
}
