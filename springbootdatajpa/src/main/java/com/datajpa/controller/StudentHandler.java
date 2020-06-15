package com.datajpa.controller;

import com.datajpa.entity.Student;
import com.datajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentHandler {
    @Autowired
    private StudentService studentService;

    @GetMapping("/findAll")
    public String findAll(Map<String, Object> map) {
        List<Student> students = studentService.findAll();
        map.put("students", students);
        return "index";
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public Student getOne(@PathVariable("id") Integer id) {
        return studentService.getOne(id);
    }

    @PostMapping("/saveAndFlush")
    @ResponseBody
    public Student saveAndFlush(Student student) {
        Student saveAndFlush = studentService.saveAndFlush(student);
        return saveAndFlush;
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
    }
}
