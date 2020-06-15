package com.arley.controller;


import com.arley.entity.User;
import com.arley.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leizige
 */
@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/selectUserList")
    public List<User> selectUserList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    @GetMapping("/selectById/{id}")
    public User selectById(@PathVariable("id") Long id) {
        User user = userMapper.selectById(id);
        return user;
    }
}
