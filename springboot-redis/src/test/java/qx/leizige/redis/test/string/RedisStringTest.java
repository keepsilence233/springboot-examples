package qx.leizige.redis.test.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import qx.leizige.redis.test.BaseTest;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * {@link <a href="https://redis.io/docs/data-types/strings/">redis string data type</a>}
 */
@Slf4j
public class RedisStringTest extends BaseTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * get/set 设置和检索字符串
     * 值可以是各种类型的字符串（包括二进制数据），例如，您可以在值中存储 jpeg 图像。值不能大于 512 MB。
     */
    @Test
    public void test() {
        //set key and expire time
        redisTemplate.opsForValue().set("mykey", "somevalue", 30, TimeUnit.SECONDS);
//        redisTemplate.expire("mykey",30,TimeUnit.SECONDS);
        log.info("mykey is value : {},expire is {}", redisTemplate.opsForValue().get("mykey"), redisTemplate.getExpire("mykey"));

        //key is exists
        Boolean hasKey = redisTemplate.hasKey("mykey");
        log.info("mykey is exists : {}", hasKey);
//        redisTemplate.countExistingKeys(Collections.singleton("mykey"));

        //delete key
        Boolean delete = redisTemplate.delete("mykey");
//        redisTemplate.delete(Collections.singleton("mykey"));
        log.info("delete mykey : {}", delete);
    }

    /**
     * redis set nx
     * set mykey newval nx
     * SET如果密钥已经存在，我可能会要求失败，或者相反，只有当密钥已经存在时它才会成功：
     */
    @Test
    public void testSetNx() {
        Boolean result = redisTemplate.opsForValue().setIfAbsent("mynxkey", "mynxvalue", 10, TimeUnit.SECONDS);
        log.info("mynxkey setIfAbsent result : {}", result);
    }


    /**
     * redis incr 原子增量。INCR命令将字符串值解析为整数，将其加一，最后将得到的值设置为新值。
     * 即使多个客户端针对同一个密钥发出 INCR，也永远不会进入竞争状态。例如，永远不会发生客户端 1 读取“10”，客户端 2 同时读取“10”，两者都递增到 11，并将新值设置为 11。
     */
    @Test
    public void testIncr() {
        redisTemplate.opsForValue().set("myincrkey", 10);

        redisTemplate.opsForValue().increment("myincrkey"); //incr
        redisTemplate.opsForValue().increment("myincrkey", 10); //incrby

        redisTemplate.opsForValue().decrement("myincrkey"); //decr
        redisTemplate.opsForValue().decrement("myincrkey", 10); //decrby
    }

    /**
     * PERSIST可以用来删除过期并使密钥永远持久
     */
    @Test
    public void testPersist() {
        redisTemplate.opsForValue().set("mypresistkey", 100, 30, TimeUnit.SECONDS);
        log.info("mypresistkey expire time : {}", redisTemplate.getExpire("mypresistkey"));

        redisTemplate.persist("mypresistkey");
        log.info("mypresistkey expire time : {}", redisTemplate.getExpire("mypresistkey"));
    }
}
