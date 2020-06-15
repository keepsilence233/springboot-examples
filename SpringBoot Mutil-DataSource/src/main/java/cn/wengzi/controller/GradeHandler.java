package cn.wengzi.controller;

import cn.wengzi.entity.secondary.Grade;
import cn.wengzi.mapper.secondarymapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeHandler {
    @Autowired
    private GradeMapper gradeMapper;


    @ResponseBody
    @GetMapping("/findAll")
    public List<Grade> findAll() {
        return gradeMapper.findAll();
    }
}
