package cn.wengzi.controller;

import cn.wengzi.eneity.Student;
import cn.wengzi.repository.impl.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengzi
 * @date 2019/10/16 22：55
 */
@Controller
@RequestMapping("/student")
public class StudentHandler {

    @Autowired
    private StudentRepositoryImpl studentRepository;

    @RequestMapping("/queryStudentAll")
    public String queryStudentAll(Model model) {
        List<Student> students = new ArrayList<Student>(studentRepository.queryStudentAll());
        model.addAttribute("students", students);
        return "index";
    }
}
