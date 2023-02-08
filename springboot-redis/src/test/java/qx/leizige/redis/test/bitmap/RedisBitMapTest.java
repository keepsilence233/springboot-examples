package qx.leizige.redis.test.bitmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import qx.leizige.redis.test.BaseTest;

/**
 * {@link <a href="https://redis.io/docs/data-types/bitmaps/">redis BitMap data type</a>}
 */
@Slf4j
public class RedisBitMapTest extends BaseTest {


    /**
     * <p>
     * SETBIT将提供的偏移量处的位设置为 0 或 1。
     * GETBIT返回给定偏移量处的位值。
     * BITOP允许您对一个或多个字符串执行按位运算。
     * </p>
     * <p>假设您在现场部署了 1000 个传感器，标记为 0-999。您想要快速确定给定传感器是否在一小时内对服务器执行了 ping 操作。</p>
     */
    @Test
    public void test() {
        //传感器 123 在 2024 年 1 月 1 日 00:00 时对服务器执行 ping 操作。
        redisTemplate.opsForValue().setBit("pings:2024-01-01-00:00", 123, true);
        //传感器 123 是否在 2024 年 1 月 1 日 00:00 时 ping 服务器？
        redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 123);
        log.info("123 在 2024 年 1 月 1 日 00:00 时 ping 服务器 : {} ", redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 123));
        //传感器 456 呢？
        redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 456);
        log.info("456 在 2024 年 1 月 1 日 00:00 时 ping 服务器 : {} ", redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 456));
    }


    /**
     * BitMap 统计用户登陆
     * <p>
     * key : user:login:status
     * offset : userId
     * 用户10023登陆 : setBit user:login:status 10023 1
     * 用户10023推出 : setBit user:login:status 10023 0
     * 判断用户是否登陆 : getBit user:login:status 10023 (返回1则代表在线)
     * 解决统计问题：bitCount user:login:status 获取在线用户数量
     * </p>
     */
    @Test
    public void statisticsUserLogin() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                RedisBitMapUtils.setBit("user:login:status", i, true);
            }
        }
        log.info("login status user count is {}", RedisBitMapUtils.bitCount("user:login:status"));
    }
}





