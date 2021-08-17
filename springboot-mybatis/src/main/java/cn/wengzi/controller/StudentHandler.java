package cn.wengzi.controller;


import cn.wengzi.pojo.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wengzi
 * @date 2019/11/5 night 22:13
 */
@RestController
@RequestMapping("/student")
public class StudentHandler {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/queryStudentAll")
    public List<Student> queryStudentAll() {
        return studentRepository.queryStudentAll();
    }

    @GetMapping("/queryStudentById/{id}")
    public Student queryStudentById(@PathVariable("id") Integer id) {
        return studentRepository.queryStudentById(id);
    }

    @GetMapping("/queryStudentJoinStudentCardByStuId/{id}")
    public Student queryStudentJoinStudentCardByStuId(@PathVariable("id") Integer id) {
        return studentRepository.queryStudentJoinStudentCardByStuId(id);
    }
}
