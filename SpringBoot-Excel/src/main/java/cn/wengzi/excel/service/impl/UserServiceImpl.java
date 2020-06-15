package cn.wengzi.excel.service.impl;

import cn.wengzi.excel.entity.User;
import cn.wengzi.excel.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public List<User> QueryAllUser() {
        List<User> users = new ArrayList<User>();
        User user1 = new User(1, "zs", "北京市丰台区", "北京");
        User user2 = new User(1, "ls", "上海市黄浦区", "上海");
        users.add(user1);
        users.add(user2);
        return users;
    }
}
