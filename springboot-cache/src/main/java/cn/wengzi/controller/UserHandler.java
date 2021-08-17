package cn.wengzi.controller;


import cn.wengzi.entity.User;
import cn.wengzi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leizige
 */
@Slf4j
@CacheConfig
@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> selectUserList() {
        return userService.selectUserList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User selectById(@PathVariable("id") Integer id) {
        return userService.selectOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Integer save(@Validated @RequestBody User user) {
        return userService.insertUser(user);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User updateUser(@Validated @RequestBody User user) {
        Integer count = userService.updateUser(user);
        if(count > 0){
            return userService.selectOne(user.getId());
        }
        return null;
    }

    @GetMapping(value = "/removeAllCacheAfter")
    public void removeAllCacheAfter() {

    }
}
