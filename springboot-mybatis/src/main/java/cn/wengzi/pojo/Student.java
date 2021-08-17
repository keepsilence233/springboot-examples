package cn.wengzi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wengzi
 * @date 2019/11/5 night 22:08
 * @description 学生信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    /**
     * 一个学生对应一个学生证
     * 一对多
     */
    private StudentCard studentCard;

    /**
     * 一个学生对应一个班级
     * 一对多关系
     */
    private StudentClass studentClass;
}
