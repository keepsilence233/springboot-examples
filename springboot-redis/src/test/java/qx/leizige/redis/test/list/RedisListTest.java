package qx.leizige.redis.test.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import qx.leizige.redis.test.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * {@link <a href="https://redis.io/docs/data-types/lists/">redis list data type</a>}
 */
@Slf4j
public class RedisListTest extends BaseTest {

    /**
     * LPUSH添加一个新元素到列表的头部；RPUSH添加到尾巴。
     * LPUSH和RPUSH都是可变命令，这意味着您可以在一次调用中自由地将多个元素推送到列表中。
     */
    @Test
    public void test() {
        redisTemplate.opsForList().rightPush("mylist", "A");    //rpush
        redisTemplate.opsForList().rightPush("mylist", "B");    //rpush
        redisTemplate.opsForList().rightPushAll("mylist", "C", "D");
        redisTemplate.opsForList().leftPush("mylist", "firstA"); //lpush
        redisTemplate.opsForList().leftPushAll("mylist", "firstB", "firstC");
        log.info("mylist key size : {}", redisTemplate.opsForList().size("mylist"));    //llen:返回列表长度

        /**
         *LRANGE有两个索引，即要返回的范围的第一个和最后一个元素。
         * 两个索引都可以是负数，告诉 Redis 从末尾开始计数：-1 是最后一个元素，-2 是列表的倒数第二个元素，依此类推。
         */
        List<Object> mylist = redisTemplate.opsForList().range("mylist", 0, -1);//lrange
        log.info("mylist object : {}", mylist);
    }


    /**
     * Redis 列表上定义的一个重要操作是弹出元素的能力。弹出元素是同时从列表中检索元素并将其从列表中删除的操作。您可以从左侧和右侧弹出元素
     */
    @Test
    public void testPop() {
        redisTemplate.opsForList().rightPushAll("mylist", "a", "b", "c");
        //rpop
        Object rpop = redisTemplate.opsForList().rightPop("mylist");
        log.info("mylist rpop {}", rpop);
        rpop = redisTemplate.opsForList().rightPop("mylist");
        log.info("mylist rpop {}", rpop);
        rpop = redisTemplate.opsForList().rightPop("mylist");
        log.info("mylist rpop {}", rpop);
        rpop = redisTemplate.opsForList().rightPop("mylist");
        log.info("mylist rpop {}", rpop);

        log.info("--- --- --- ----");

        redisTemplate.opsForList().rightPushAll("mylist", "a", "b", "c");
        //lpop
        Object lpop = redisTemplate.opsForList().leftPop("mylist");
        log.info("mylist lpop {}", lpop);
        lpop = redisTemplate.opsForList().leftPop("mylist");
        log.info("mylist lpop {}", lpop);
        lpop = redisTemplate.opsForList().leftPop("mylist");
        log.info("mylist lpop {}", lpop);
        lpop = redisTemplate.opsForList().leftPop("mylist");
        log.info("mylist lpop {}", lpop);
    }

    /**
     * 上限列表
     * Redis 允许我们将列表用作上限集合，只记住最新的 N 项并使用LTRIM命令丢弃所有最旧的项。
     */
    @Test
    public void testTrim() {
        redisTemplate.opsForList().rightPushAll("mylist", 1, 2, 3, 4, 5);
        //ltrim 将列表减少到指定的元素范围。
        redisTemplate.opsForList().trim("mylist", 0, 2);
        log.info("mylist object : {}", redisTemplate.opsForList().range("mylist", 0, -1));
    }


    /**
     * 列表上的阻塞操作
     * 列表有一个特殊的特性，使它们适合于实现队列，并且通常作为进程间通信系统的构建块：阻塞操作。
     * <p>
     * BLPOP从列表的头部移除并返回一个元素。如果列表为空，则命令将阻塞，直到元素可用或达到指定的超时为止。
     * BLMOVE原子地将元素从源列表移动到目标列表。如果源列表为空，则该命令将阻塞，直到有新元素可用为止。
     * </p>
     */
    @Test
    public void testBrpop() {
        redisTemplate.opsForList().rightPushAll("tasks", 1, 2, 3);
        //blpop/brpop 阻塞式（推荐使用）
        Object brpop = redisTemplate.opsForList().rightPop("tasks", 2, TimeUnit.SECONDS);
        log.info("tasks brpop {}", brpop);
        brpop = redisTemplate.opsForList().rightPop("tasks", 2, TimeUnit.SECONDS);
        log.info("tasks brpop {}", brpop);
        brpop = redisTemplate.opsForList().rightPop("tasks", 2, TimeUnit.SECONDS);
        log.info("tasks brpop {}", brpop);
        brpop = redisTemplate.opsForList().rightPop("tasks", 2, TimeUnit.SECONDS);
        log.info("tasks brpop {}", brpop);
    }
}
