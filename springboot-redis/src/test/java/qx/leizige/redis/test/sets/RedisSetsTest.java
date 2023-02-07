package qx.leizige.redis.test.sets;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import qx.leizige.redis.test.BaseTest;

import java.util.Set;

/**
 * {@link <a href="https://redis.io/docs/data-types/sets/">redis sets data type</a>}
 */
@Slf4j
public class RedisSetsTest extends BaseTest {


    /**
     * <p>
     * SADD将新成员添加到集合中。
     * SREM从集合中删除指定的成员。
     * SISMEMBER测试集合成员的字符串。
     * SINTER返回两个或多个集合共有的成员集合（即交集）。
     * SCARD返回集合的大小（又名基数）。
     * </p>
     */
    @Test
    public void test() {
        redisTemplate.opsForSet().add("user:123:favorites", 347, 561, 742); //SADD
        redisTemplate.opsForSet().add("user:456:favorites", 561); //SADD

        Set<Object> members = redisTemplate.opsForSet().members("user:123:favorites");  //SMEMBERS
        Boolean isMember = redisTemplate.opsForSet().isMember("user:123:favorites", 742);  //SMEMBERS
        log.info("user:123:favorites key member is {} ,isMember {}", members, isMember);

        redisTemplate.opsForSet().move("user:123:favorites", 347, "user:456:favorites");    //SMOVE
        log.info("user:123:favorites move after  {} ", redisTemplate.opsForSet().members("user:123:favorites"));
        log.info("user:456:favorites object  {} ", redisTemplate.opsForSet().members("user:456:favorites"));

        Long size = redisTemplate.opsForSet().size("user:123:favorites");
        log.info("user:123:favorites key size is {}", size);

        Cursor<Object> cursor = redisTemplate.opsForSet().scan("user:123:favorites", ScanOptions.scanOptions().match("742").build());   //SSCAN
        while (cursor.hasNext()) {
            Object next = cursor.next();
            log.info("user:123:favorites match object : {}", next);
        }

    }

}
