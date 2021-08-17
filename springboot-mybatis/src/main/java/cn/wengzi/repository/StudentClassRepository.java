package cn.wengzi.repository;

import cn.wengzi.pojo.StudentClass;

/**
 * @author wengzi
 * @date 2019/11/5 night 23:02
 */
public interface StudentClassRepository {

    /**
     * 查询一个班级包含学生
     *
     * @param classId 班级id
     * @return
     */
    StudentClass queryStudentClassJoinStudentByStuClassId(Integer classId);
}
