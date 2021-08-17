package cn.wengzi.controller;

import cn.wengzi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author wengzi
 * @date 19/10/5 17:58
 */
@RestController
@RequestMapping("/student")
public class StudentHandler {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody Student student) {
        redisTemplate.opsForValue().set("student", student);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key) {
        redisTemplate.delete(key);
        //删除之后判断key是否存在,不存在返回false
        return redisTemplate.hasKey(key);
    }

    /**
     * 字符串
     */
    @GetMapping("/string")
    public void StringTest() {
        redisTemplate.opsForValue().set("str", "HelloWorld");
        System.out.println(redisTemplate.opsForValue().get("str"));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public void ListTest() {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush("list", "Hello");
        list.leftPush("list", "World");
        list.leftPush("list", "Java");
        List<Object> getList = list.range("list", 0, 2);
        for (Object values : getList) {
            System.out.println(values);
        }
    }

    /**
     * 集合
     */
    @GetMapping("/set")
    public void setTest() {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add("set", "Hello");
        set.add("set", "Hello");
        set.add("set", "World");
        set.add("set", "World");
        set.add("set", "Java");
        set.add("set", "Java");
        Set<Object> getSet = set.members("set");
        for (Object val : getSet) {
            System.out.println(val);
        }
    }


    /**
     * 有序集合
     */
    @GetMapping("/zset")
    public void zsetTest() {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add("zset", "Hello", 1);
        zset.add("zset", "World", 2);
        zset.add("zset", "Java", 3);
        Set<Object> set = zset.range("zset", 0, 2);
        for (Object val : set) {
            System.out.println(val);
        }
    }

    /**
     * 哈希
     */
    @GetMapping("/hash")
    public void hashTest() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("key", "hashkey", "Hello");
        System.out.println(hash.get("key", "hashkey"));
    }
}
