package cn.wengzi.repository.impl;

import cn.wengzi.entity.Teacher;
import cn.wengzi.repository.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzi
 * @date 2019/11/5 night 19:35
 */
@Component
public class TeacherRepositoryImpl implements TeacherRepository {

    private static Map<Long, Teacher> dbMap = null;

    static {
        dbMap = new HashMap<Long, Teacher>();
        dbMap.put(1L, new Teacher(1L, "张三", 20));
        dbMap.put(2L, new Teacher(2L, "李四", 25));
        dbMap.put(3L, new Teacher(3L, "王五", 30));
    }

    @Override
    public List<Teacher> queryTeacherAll() {
        return new ArrayList<Teacher>(dbMap.values());
    }

    @Override
    public Teacher queryTeacherById(Long id) {
        return dbMap.get(id);
    }
}
