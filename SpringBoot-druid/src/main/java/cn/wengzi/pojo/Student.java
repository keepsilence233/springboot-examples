package cn.wengzi.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author wengzi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
