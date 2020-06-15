package cn.wengzi.controller;

import cn.wengzi.entity.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wengzi
 */
@RestController
@RequestMapping("/student")
public class StudentHandler {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/queryStudentAll")
    public ResponseEntity<List<Student>> queryStudentAll() {
        List<Student> students = studentRepository.queryStudentAll();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @GetMapping("/queryStudentById/{id}")
    public ResponseEntity<Student> queryStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<Student>(studentRepository.queryStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PutMapping("/upd")
    public void upd(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
    }
}
