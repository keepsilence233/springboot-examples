package cn.wengzi.repository;

import cn.wengzi.entity.Student;

import java.util.Collection;
import java.util.List;

/**
 * @author wengzi
 */
public interface StudentRepository {
    List<Student> queryStudentAll();

    Student queryStudentById(Long id);

    void save(Student student);

    void upd(Student student);

    void deleteById(Long id);
}
