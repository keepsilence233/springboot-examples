package cn.wengzi.excel.Controller;

import cn.gjing.tools.excel.ExcelFactory;
import cn.wengzi.excel.entity.User;
import cn.wengzi.excel.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ExcelHandler {

    @Value("${Excel.fileName}")
    private String fileName;    //在yml文件中定义导出Excel文件名

    @Autowired
    private UserService userService;

    @GetMapping("/downloadNullExcelLocal")
    @ApiOperation(value = "excel空文件导出")
    public void downExcel2(HttpServletResponse response) {
        ExcelFactory.createWriter(fileName, User.class, response, null).write();
    }

    @GetMapping("/downloadExcelLocal")
    @ApiOperation(value = "excel导出")
    public void downExcel(HttpServletResponse response) throws UnsupportedEncodingException {
        //模拟访问数据库,将数据库中的数据导出到Excel
        List<User> userList = userService.QueryAllUser();
        ExcelFactory.createWriter(fileName, User.class, response, userList).write();
    }

    @PostMapping("/excelImport")
    @ApiOperation(value = "excel导入")
    public ResponseEntity<List<User>> excelImport(@RequestParam("excelfile") MultipartFile file) {
        List<User> readExcel = null;
        try {
            readExcel = ExcelFactory.createReader(file.getInputStream(), User.class).read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(readExcel);
    }
}
