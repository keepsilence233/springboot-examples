package cn.wengzi.controller;

import cn.wengzi.entity.Student;
import cn.wengzi.entity.Teacher;
import cn.wengzi.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wengzi
 * @date 2019/11/5 night 19:38
 */
@Api(value = "TeacherHandler", tags = {"学生管理", "教师管理"})
@RestController
@RequestMapping("/student")
public class TeacherHandler {

    @Autowired
    private TeacherRepository teacherRepository;

    @ApiOperation(value = "获取教师列表", notes = "")
    @GetMapping("/queryTeacherAll")
    public ResponseEntity<List<Teacher>> queryTeacherAll() {
        List<Teacher> teachers = teacherRepository.queryTeacherAll();
        return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
    }

    @ApiOperation(value = "获取教师详细信息", notes = "根据URL传过来的ID来获取教师详细信息")
    @ApiImplicitParam(name = "id", value = "教师ID", paramType = "path", required = true, dataType = "Teacher")
    @GetMapping("/queryTeacherById/{id}")
    public ResponseEntity<Teacher> queryTeacherById(@PathVariable("id") Long id) {
        Teacher teacher = teacherRepository.queryTeacherById(id);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }
}
