package cn.wengzi.excel.mapper;

import cn.wengzi.excel.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    List<User> QueryAllUser();
}
