package cn.wengzi.transaction;

import cn.wengzi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wengzi
 * @data 2019/10/12 21:46
 * @Description Redis 事务
 */
@Component
public class RedisTransaction {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    Student student = new Student(1L, "zs", 23, LocalDateTime.now());

    SessionCallback sessionCallback = new SessionCallback() {
        @Override
        public Student execute(RedisOperations redisOperations) throws DataAccessException {
            //事务开始
            redisOperations.multi();
            redisOperations.boundValueOps("student").set(student);

            //命令进入队列,而没有被执行,所以使用get命令返回为 null
            Student student = (Student) redisOperations.opsForValue().get("student");
            //事务执行过程中,命令进入队列,而没有被执行,所以 value 为空.

            //此时list会保存之前进入队列的所有命令的结果
            //执行事务
            List list = redisOperations.exec();

            //事务结束后,获取 value
            student = (Student) redisTemplate.opsForValue().get("student");

            return student;
        }
    };


}
