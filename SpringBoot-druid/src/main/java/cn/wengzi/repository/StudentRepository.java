package cn.wengzi.repository;

import cn.wengzi.pojo.Student;

import java.util.List;

/**
 * @author wengzi
 */
public interface StudentRepository {
    List<Student> queryStudentAll();
}
