package cn.wengzi.repository;

import cn.wengzi.entity.Teacher;

import java.util.List;

/**
 * @author wengzi
 * @date 2019/11/5 night 19:32
 */
public interface TeacherRepository {
    List<Teacher> queryTeacherAll();

    Teacher queryTeacherById(Long id);
}
