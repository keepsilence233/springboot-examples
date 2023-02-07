package qx.leizige.redis.test.hashes;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import qx.leizige.redis.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link <a href="https://redis.io/docs/data-types/hashes/">redis hashes data type</a>}
 */
@Slf4j
public class RedisHashTest extends BaseTest {


    /**
     * <p>
     * HSET设置散列上一个或多个字段的值。
     * HGET返回给定字段的值。
     * HMGET返回一个或多个给定字段的值。
     * HINCRBY通过提供的整数增加给定字段的值。
     * </p>
     */
    @Test
    public void test() {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "antirez");
        params.put("birthyear", 1997);
        params.put("verified", 1);
        redisTemplate.opsForHash().putAll("user:1000", params); //HSET

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("user:1000");  //HMGET
        log.info("user:1000 entries : {}", entries);

        if (redisTemplate.opsForHash().hasKey("user:1000", "username")) {   // HEXISTS
            Object username = redisTemplate.opsForHash().get("user:1000", "username"); //HGET
            log.info("hget username = {}", username);
        }

        log.info("user:1000 birthyear is : {}", redisTemplate.opsForHash().get("user:1000", "birthyear"));

        redisTemplate.opsForHash().increment("user:1000", "birthyear", 10);     //HINCRBY
        log.info("user:1000 birthyear increment after : {}", redisTemplate.opsForHash().get("user:1000", "birthyear"));
    }
}
