package cn.wengzi.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wengzi
 * @date 19/10/5 17:58
 * @Description Student entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = -3894800747380828797L;
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime birthday;
}
