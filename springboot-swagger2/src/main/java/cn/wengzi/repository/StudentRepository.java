package cn.wengzi.repository;

import cn.wengzi.entity.Student;

import java.util.Collection;

/**
 * @author wengzi
 * @date 2019/10/17 下午15:19
 */
public interface StudentRepository {
    Collection queryStudentAll();

    Student queryStudentById(Long id);

    void addStudent(Student student);

    void updStudent(Student student);

    void delStudent(Long id);
}
