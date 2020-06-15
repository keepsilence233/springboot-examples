package cn.wengzi.controller;

import cn.wengzi.pojo.StudentClass;
import cn.wengzi.repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzi
 * @date 2019/11/5 night 23:20
 */
@RestController
@RequestMapping("/studentClass")
public class StudentClassHandler {

    @Autowired
    private StudentClassRepository studentClassRepository;


    @GetMapping("/queryStudentClassJoinStudentByStuClassId/{classId}")
    public StudentClass queryStudentClassJoinStudentByStuClassId(@PathVariable("classId") Integer classId) {
        return studentClassRepository.queryStudentClassJoinStudentByStuClassId(classId);
    }
}
