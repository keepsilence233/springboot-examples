package cn.wengzi.controller.api;

import cn.wengzi.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "StudentHandler", tags = {"学生管理", "教师管理"})
public interface StudentApi {

    @ApiOperation(value = "获取学生列表", notes = "")
    ResponseEntity<List<Student>> queryStudentAll();

    @ApiOperation(value = "添加学生", notes = "根据Student对象添加学生")
    @ApiImplicitParam(name = "student", value = "学生详细实体student", required = true, dataType = "Student")
    ResponseEntity<Void> addStudent(Student student);

    @ApiOperation(value = "获取学生详细信息", notes = "根据URL的Id来获取学生详细信息")
    @ApiImplicitParam(name = "id", value = "学生Id", paramType = "path", required = true, dataType = "Long")
    ResponseEntity<Student> queryStudentById(Long id);


    @ApiOperation(value = "更新学生详细信息", notes = "根据URL传过来的Id指定更新对象,并根据传过来的Student更新学生详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "学生Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "student", value = "学生详细实体student", required = true, dataType = "Student")
    })
    ResponseEntity<Void> updStudent(Student student);

    @ApiOperation(value = "删除学生", notes = "根据URL传过来的Id删除对象")
    @ApiImplicitParam(name = "Id", value = "学生Id", paramType = "path", required = true, dataType = "Long")
    ResponseEntity<Void> delStudent(Long id);
}
