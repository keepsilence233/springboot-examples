package cn.wengzi.controller;

import cn.wengzi.entity.Student;
import cn.wengzi.repository.StudentRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wengzi
 * @date 2019/10/16 night 23:51
 */
@Api(value = "StudentHandler", tags = {"学生管理", "教师管理"})
@RestController
@RequestMapping("/student")
public class StudentHandler {

    @Autowired
    private StudentRepository studentRepository;

    @ApiOperation(value = "获取学生列表", notes = "")
    @GetMapping("/queryStudentAll")
    public ResponseEntity<List<Student>> queryStudentAll() {
        List<Student> students = new ArrayList<Student>(studentRepository.queryStudentAll());
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @ApiOperation(value = "添加学生", notes = "根据Student对象添加学生")
    @ApiImplicitParam(name = "student", value = "学生详细实体student", required = true, dataType = "Student")
    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentRepository.addStudent(student);
    }

    @ApiOperation(value = "获取学生详细信息", notes = "根据URL的Id来获取学生详细信息")
    @ApiImplicitParam(name = "id", value = "学生Id", paramType = "path", required = true, dataType = "Long")
    @GetMapping("/queryStudentById/{id}")
    public ResponseEntity<Student> queryStudentById(@PathVariable("id") Long id) {
        Student student = studentRepository.queryStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "更新学生详细信息", notes = "根据URL传过来的Id指定更新对象,并根据传过来的Student更新学生详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "学生Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "student", value = "学生详细实体student", required = true, dataType = "Student")
    })
    @PutMapping("/updStudent")
    public void updStudent(@RequestBody Student student) {
        studentRepository.updStudent(student);
    }

    @ApiOperation(value = "删除学生", notes = "根据URL传过来的Id删除对象")
    @ApiImplicitParam(name = "Id", value = "学生Id", paramType = "path", required = true, dataType = "Long")
    @DeleteMapping("/delStudent/{id}")
    public void delStudent(@PathVariable("id") Long id) {
        studentRepository.delStudent(id);
    }
}
