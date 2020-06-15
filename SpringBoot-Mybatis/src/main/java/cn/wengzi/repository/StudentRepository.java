package cn.wengzi.repository;

import cn.wengzi.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengzi
 * @date 2019/11/5 night 22:17
 */
public interface StudentRepository {

    /**
     * 查询学生列表
     *
     * @return
     */
    List<Student> queryStudentAll();

    /**
     * 根据id查询具体学生信息
     *
     * @param id 学生id
     * @return
     */
    Student queryStudentById(@Param("id") Integer id);

    /**
     * 查询学生信息和学生证信息
     *
     * @param id 学生id
     * @return 学生和学生证一对一关系
     */
    Student queryStudentJoinStudentCardByStuId(Integer id);

}
