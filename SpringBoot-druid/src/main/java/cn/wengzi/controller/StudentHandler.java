package cn.wengzi.controller;

import cn.wengzi.pojo.Student;
import cn.wengzi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wengzi
 */
@RestController
@RequestMapping("/student")
public class StudentHandler {

    private final Logger logger = LoggerFactory.getLogger(StudentHandler.class);

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/queryStudentAll")
    public ResponseEntity<List<Student>> queryStudentAll() {
        List<Student> students = studentRepository.queryStudentAll();
        logger.info(students.toString());
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

}
