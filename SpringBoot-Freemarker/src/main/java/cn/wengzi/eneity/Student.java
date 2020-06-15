package cn.wengzi.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wengzi
 * @date 2019/10/16 22:43
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -7472667404696496501L;

    private Long id;
    private String name;
    private Integer age;
    private String address;
}
