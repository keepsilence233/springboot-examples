package cn.wengzi.repository;


import java.util.Collection;

/**
 * @author wengzi
 * @date 2019/10/16 22:49
 */
public interface StudentRepository {

    /**
     * 查询全部学生
     *
     * @return
     */
    Collection queryStudentAll();
}
