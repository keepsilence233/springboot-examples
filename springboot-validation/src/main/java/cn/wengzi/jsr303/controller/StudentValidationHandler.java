package cn.wengzi.jsr303.controller;

import cn.wengzi.jsr303.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzi
 * @date 19/10/5 21:23
 */
@Controller
@RequestMapping("/student")
public class StudentValidationHandler {

    /**
     * 在需要校验的参数实体前添加@Valid注解
     *
     * @return success
     * @Valid: 代表里面的对象的数据要进行校验
     * BindingResult result:存放校验结果
     */
    @PostMapping("/ValidationStudent")
    public @ResponseBody
    String ValidationStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败,返回错误信息并在模态框中显示
            Map<String, Object> map = new HashMap<>(16);
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                //return fieldError.getDefaultMessage();
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        return "success";
    }
}
