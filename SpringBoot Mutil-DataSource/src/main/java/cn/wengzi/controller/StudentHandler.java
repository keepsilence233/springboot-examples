package cn.wengzi.controller;

import cn.wengzi.entity.primary.Student;
import cn.wengzi.mapper.primarymapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentHandler {

    @Autowired
    private StudentMapper studentMapper;

    @ResponseBody
    @GetMapping("/findAll")
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
