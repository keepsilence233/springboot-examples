package qx.leizige.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private Integer status;


    public static List<Student> build() {
        List<Student> result = new ArrayList<>();
        result.add(new Student(1, "zs", 23, 0));
        result.add(new Student(2, "ls", 24, 0));
        result.add(new Student(3, "ww", 25, 0));
        result.add(new Student(4, "zl", 26, 0));
        return result;
    }

}
