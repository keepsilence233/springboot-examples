package cn.wengzi.service.impl;

import cn.wengzi.entity.User;
import cn.wengzi.mapper.UserMapper;
import cn.wengzi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Arley
 * @date 2020/03/08 night 20:14
 * desc:带缓存的service
 * <br>
 * 注意点:
 * Cacheable、CachePut、CacheEvict 必须要有 cacheNames
 * 注解必须放在public修饰的方法上
 * 如果只是获取缓存使用@Cacheable即可,如果要更新数据库并且更新缓存一定要使用@CachePut,否则@Cacheable会出现脏读
 * </br>
 */
@Slf4j
//公共配置  可以在类上注释 注释本类的 缓存相关公共配置
//@CacheConfig(cacheNames = "userCache")
@Service(value = "userServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    // 标志读取缓存操作,如果缓存不存在,则调用目标方法,并将结果放入缓存
    @Cacheable(cacheNames = "userList")
    public List<User> selectUserList() {
        return userMapper.queryUserList();
    }

    @Override
    //如果缓存存在,直接读取缓存值;如果缓存不存在,则调用目标方法,并将结果放入缓存
    @Cacheable(cacheNames = "user", key = "#id")
    public User selectOne(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    //写入缓存,key为user.id,一般该注解标注在新增方法上
    @CachePut(cacheNames = {"user"}, key = "#user.id")
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    //根据key清除缓存,一般该注解标注在修改和删除方法上
    @CacheEvict(cacheNames = {"user"}, key = "#user.id")
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
//    @CacheEvict(value = "userCache", allEntries = true)//方法调用后清空所有缓存
    public void removeAllCacheAfter() {
    }

    @Override
//    @CacheEvict(value = "userCache", beforeInvocation = true)//方法调用前清空所有缓存
    public void removeAllCacheBefore() {
    }
}
