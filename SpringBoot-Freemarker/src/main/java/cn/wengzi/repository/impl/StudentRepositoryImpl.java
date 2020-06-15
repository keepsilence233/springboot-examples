package cn.wengzi.repository.impl;

import cn.wengzi.eneity.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wengzi
 * @date 2019/10/16 22:53
 */
@Component
public class StudentRepositoryImpl implements StudentRepository {

    private static HashMap<Long, Student> dbStudent = null;

    static {
        dbStudent = new HashMap<Long, Student>();
        dbStudent.put(1L, new Student(1L, "张三", 23, "山西"));
        dbStudent.put(2L, new Student(2L, "李四", 24, "北京"));
        dbStudent.put(3L, new Student(3L, "王五", 25, "上海"));
    }

    public Collection queryStudentAll() {
        return dbStudent.values();
    }
}
