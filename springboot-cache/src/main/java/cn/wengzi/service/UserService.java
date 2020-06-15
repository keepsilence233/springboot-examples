package cn.wengzi.service;

import cn.wengzi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arley
 */
@Service
public interface UserService {
    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> selectUserList();

    /**
     * 根据id获取用户
     *
     * @return
     */
    User selectOne(Integer id);

    /**
     * 新增用户
     *
     * @param user
     */
    Integer insertUser(User user);

    /**
     * 修改用户
     *
     * @param user
     */
    Integer updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    Integer deleteUser(Integer id);

    /**
     * 方法调用后清空所有cache
     */
    void removeAllCacheAfter();

    void removeAllCacheBefore();
}
