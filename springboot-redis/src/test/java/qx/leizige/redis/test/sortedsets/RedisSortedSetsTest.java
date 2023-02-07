package qx.leizige.redis.test.sortedsets;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import qx.leizige.redis.test.BaseTest;

import java.util.Set;

/**
 * {@link <a href="https://redis.io/docs/data-types/sorted-sets/">redis sorted sets data type</a>}
 */
@Slf4j
public class RedisSortedSetsTest extends BaseTest {


    @Test
    public void test() {

        //ZADD 向集合中插入元素，并设置分数
        redisTemplate.opsForZSet().add("user:aa", "A", System.currentTimeMillis());
        redisTemplate.opsForZSet().add("user:aa", "B", System.currentTimeMillis());
        redisTemplate.opsForZSet().add("user:aa", "C", System.currentTimeMillis());
        redisTemplate.opsForZSet().add("user:aa", "D", System.currentTimeMillis());
        redisTemplate.opsForZSet().add("user:aa", "E", System.currentTimeMillis());

        //ZCARD
        Long zCard = redisTemplate.opsForZSet().zCard("user:aa");
        log.info("user:aa zCard is : {}", zCard);

        //ZRANGEBYSCORE
        Set<Object> rangeByScore = redisTemplate.opsForZSet().rangeByScore("user:aa", 1, Long.MAX_VALUE, 0, 5);
        log.info("user:aa rangeByScore value : {}", rangeByScore);

        //ZREVRANGEBYSCORE
        Set<Object> reverseRangeByScore = redisTemplate.opsForZSet().reverseRangeByScore("user:aa", 1, Long.MAX_VALUE, 0, 5);
        log.info("user:aa reverseRangeByScore value : {}", reverseRangeByScore);

        //通过 ZREVRANGEBYSCORE 模拟倒序分页效果
        for (int i = 1; i <= 3; i++) {
            int currentPage = i;
            int pageSize = 2;
            currentPage = (currentPage - 1) * pageSize;
            Set<Object> data = redisTemplate.opsForZSet().reverseRangeByScore("user:aa", 1, Long.MAX_VALUE, currentPage, pageSize);
            log.info("currentPage {} pageSize {} data {}", currentPage, pageSize, data);
        }
    }

}
