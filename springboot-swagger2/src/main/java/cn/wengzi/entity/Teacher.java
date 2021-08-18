package cn.wengzi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wengzi
 * @date 2019/11/5 night 19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {
    private Long id;
    private String name;
    private Integer age;
}
