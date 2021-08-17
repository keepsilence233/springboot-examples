package cn.wengzi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author wengzi
 * @date 2019/11/5 night 22:08
 * @description 学生班级
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentClass {
    private Integer classId;
    private String className;

    /**
     * 一个班级对应多个学生
     * 一对多关系
     */
    private List<Student> students;
}
