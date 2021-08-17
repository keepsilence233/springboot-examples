package cn.wengzi.service;

import cn.wengzi.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leizige
 */
@Service
public interface UserService {
    /**
     * 获取所有用户
     */
    List<User> selectUserList();

    /**
     * 根据id获取用户
     */
    User selectOne(Integer id);

    /**
     * 新增用户
     */
    Integer insertUser(User user);

    /**
     * 修改用户
     */
    Integer updateUser(User user);

    /**
     * 删除用户
     */
    Integer deleteUser(Integer id);

    /**
     * 方法调用后清空所有cache
     */
    void removeAllCacheAfter();

    void removeAllCacheBefore();
}
