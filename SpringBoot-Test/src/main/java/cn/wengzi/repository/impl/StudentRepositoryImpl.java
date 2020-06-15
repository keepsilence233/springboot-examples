package cn.wengzi.repository.impl;

import cn.wengzi.entity.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wengzi
 * @description 模拟数据库数据
 */
@Service
public class StudentRepositoryImpl implements StudentRepository {

    private static Map<Object, Student> dbStudentMap = null;

    static {
        dbStudentMap = Collections.synchronizedMap(new HashMap<Object, Student>());
        dbStudentMap.put(1L, new Student(1L, "张三", 23));
        dbStudentMap.put(2L, new Student(2L, "李四", 24));
        dbStudentMap.put(3L, new Student(3L, "王五", 25));
    }

    public List<Student> queryStudentAll() {
        List<Student> students = new ArrayList<Student>(dbStudentMap.values());
        return students;
    }

    public Student queryStudentById(Long id) {
        return dbStudentMap.get(id);
    }

    public void save(Student student) {
        dbStudentMap.put(student.getId(), student);
    }

    public void upd(Student student) {
        dbStudentMap.put(student.getId(), student);
    }

    public void deleteById(Long id) {
        dbStudentMap.remove(id);
    }
}
