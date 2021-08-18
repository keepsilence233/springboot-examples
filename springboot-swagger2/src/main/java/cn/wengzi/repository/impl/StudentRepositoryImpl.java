package cn.wengzi.repository.impl;

import cn.wengzi.entity.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengzi
 * @date 2019/10/17 下午15:22
 */
@Component
public class StudentRepositoryImpl implements StudentRepository {
    static Map<Long, Student> studentMap = null;

    static {
        studentMap = Collections.synchronizedMap(new HashMap<Long, Student>());
        studentMap.put(1L, new Student(1L, "张三", 23, "山西"));
        studentMap.put(2L, new Student(2L, "李四", 24, "北京"));
        studentMap.put(3L, new Student(3L, "王五", 25, "上海"));
    }

    @Override
    public Collection queryStudentAll() {
        return studentMap.values();
    }

    @Override
    public Student queryStudentById(Long id) {
        return studentMap.get(id);
    }

    @Override
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public void updStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public void delStudent(Long id) {
        studentMap.remove(id);
    }
}
