package qx.leizige.redis.test.hyperlog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import qx.leizige.redis.test.BaseTest;

/**
 * Hyperloglog，最适合通过减少内存消耗来统计不同的用户操作。
 * {@link <a href="https://redis.io/docs/data-types/hyperloglogs/">redis HyperLogLog data type</a>}
 */
@Slf4j
public class RedisHyperLogLogTest extends BaseTest {


    /**
     * PFADD向 HyperLogLog 添加一个项目。
     * PFCOUNT返回集合中项目数的估计值。
     * PFMERGE将两个或多个 HyperLogLog 合并为一个。
     */
    @Test
    public void test() {

        //假设，用户 A、B、C、D、E 和 F 在下午 18 点至 19 点之间登录了系统。
        redisTemplate.opsForHyperLogLog().add("USER:LOGIN:2019092818", "A");
        redisTemplate.opsForHyperLogLog().add("USER:LOGIN:2019092818", "B", "C", "D", "E", "F");

        Long size = redisTemplate.opsForHyperLogLog().size("USER:LOGIN:2019092818");
        log.info("USER:LOGIN:2019092818 size is {}", size);

        //假设，用户 A、B、C、D、E 和 F 在下午 18 点至 19 点之间登录了系统。
        redisTemplate.opsForHyperLogLog().add("USER:LOGIN:2019092818", "A");
        redisTemplate.opsForHyperLogLog().add("USER:LOGIN:2019092818", "B", "C", "D", "E", "F");

        size = redisTemplate.opsForHyperLogLog().size("USER:LOGIN:2019092818");
        log.info("USER:LOGIN:2019092818 size is {}", size);


        //如果用户 A~F 和另外一个其他用户 G 在下午 19 点至下午 20 点之间登录系统：
        redisTemplate.opsForHyperLogLog().add("USER:LOGIN:2019092819", "A", "B", "C", "D", "E", "F", "G");

        //合并计数两个时间点登陆用户数量
        size = redisTemplate.opsForHyperLogLog().size("USER:LOGIN:2019092818", "USER:LOGIN:2019092819");
        log.info("USER:LOGIN:2019092818 and USER:LOGIN:2019092819 size is {}", size);

        //合并两个时间点内登陆用户
        redisTemplate.opsForHyperLogLog().union("USER:LOGIN:MERGE", "USER:LOGIN:2019092818", "USER:LOGIN:2019092819");
        log.info("USER:LOGIN:2019092818 size is {}", size);
    }

    /**
     * 比较Redis Set 和 HyperLogLog 两种方式存储不同用户登录行为的内存占用
     * {@link <a href="https://segmentfault.com/a/1190000020523110">...</a>}
     */
    @Test
    public void compareSetAndHyperLogLog() {
        for (int i = 0; i < 100000; ++i) { // 100万用户
            redisTemplate.opsForSet().add("SET:USER:LOGIN:2019082811", "USER" + i);
            redisTemplate.opsForHyperLogLog().add("PF:USER:LOGIN:2019082811", "USER" + i);
        }

        log.info("SET:USER:LOGIN:2019082811 size is {}", redisTemplate.opsForSet().size("SET:USER:LOGIN:2019082811"));
        log.info("PF:USER:LOGIN:2019082811 size is {}", redisTemplate.opsForHyperLogLog().size("PF:USER:LOGIN:2019082811"));

    }
}
