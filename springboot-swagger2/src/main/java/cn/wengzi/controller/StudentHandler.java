package cn.wengzi.controller;

import cn.wengzi.controller.api.StudentApi;
import cn.wengzi.entity.Student;
import cn.wengzi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengzi
 * @date 2019/10/16 night 23:51
 */
@RestController
@RequestMapping("/student")
public class StudentHandler implements StudentApi {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/queryStudentAll")
    public ResponseEntity<List<Student>> queryStudentAll() {
        List<Student> students = new ArrayList<Student>(studentRepository.queryStudentAll());
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentRepository.addStudent(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/queryStudentById/{id}")
    public ResponseEntity<Student> queryStudentById(@PathVariable("id") Long id) {
        Student student = studentRepository.queryStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }


    @PutMapping("/updStudent")
    public ResponseEntity<Void> updStudent(@RequestBody Student student) {
        studentRepository.updStudent(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delStudent/{id}")
    public ResponseEntity<Void> delStudent(@PathVariable("id") Long id) {
        studentRepository.delStudent(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
