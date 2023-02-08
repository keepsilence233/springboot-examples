package qx.leizige.redis.test.bitmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisBitMapUtils {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisBitMapUtils.redisTemplate = redisTemplate;
    }

    /**
     * 设置key字段第offset位bit数值
     *
     * @param key    字段
     * @param offset 位置
     * @param value  数值
     */
    @Nullable
    public static void setBit(String key, long offset, boolean value) {
        redisTemplate.execute((RedisCallback) con -> con.setBit(key.getBytes(), offset, value));
    }

    /**
     * 判断该key字段offset位否为1
     *
     * @param key    字段
     * @param offset 位置
     * @return 结果
     */
    public static boolean getBit(String key, long offset) {
        return (boolean) redisTemplate.execute((RedisCallback) con -> con.getBit(key.getBytes(), offset));

    }

    /**
     * 统计key字段value为1的总数
     *
     * @param key 字段
     * @return 总数
     */
    public static Long bitCount(String key) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    /**
     * 统计key字段value为1的总数,从start开始到end结束
     *
     * @param key   字段
     * @param start 起始
     * @param end   结束
     * @return 总数
     */
    public static Long bitCount(String key, Long start, Long end) {
        return (Long) redisTemplate.execute((RedisCallback) con -> con.bitCount(key.getBytes(), start, end));
    }

    /**
     * 取多个key并集并计算总数
     *
     * @param key key
     * @return 总数
     */
    public static Long OpOrCount(String... key) {
        byte[][] keys = new byte[key.length][];
        for (int i = 0; i < key.length; i++) {
            keys[i] = key[i].getBytes();
        }
        redisTemplate.execute((RedisCallback) con -> con.bitOp(RedisStringCommands.BitOperation.OR, (key[0] + "To" + key[key.length - 1]).getBytes(), keys));
        redisTemplate.expire(key[0] + "To" + key[key.length - 1], 10, TimeUnit.SECONDS);
        return RedisBitMapUtils.bitCount(key[0] + "To" + key[key.length - 1]);
    }

    /**
     * 取多个key的交集并计算总数
     *
     * @param key key
     * @return 总数
     */
    public static Long OpAndCount(String... key) {
        byte[][] keys = new byte[key.length][];
        for (int i = 0; i < key.length; i++) {
            keys[i] = key[i].getBytes();
        }
        redisTemplate.execute((RedisCallback) con -> con.bitOp(RedisStringCommands.BitOperation.AND, (key[0] + "To" + key[key.length - 1]).getBytes(), keys));
        redisTemplate.expire(key[0] + "To" + key[key.length - 1], 10, TimeUnit.SECONDS);
        return RedisBitMapUtils.bitCount(key[0] + "To" + key[key.length - 1]);
    }

    /**
     * 取多个key的补集并计算总数
     *
     * @param key key
     * @return 总数
     */
    public static Long OpXorCount(String... key) {
        byte[][] keys = new byte[key.length][];
        for (int i = 0; i < key.length; i++) {
            keys[i] = key[i].getBytes();
        }
        redisTemplate.execute((RedisCallback) con -> con.bitOp(RedisStringCommands.BitOperation.XOR, (key[0] + "To" + key[key.length - 1]).getBytes(), keys));
        redisTemplate.expire(key[0] + "To" + key[key.length - 1], 10, TimeUnit.SECONDS);
        return RedisBitMapUtils.bitCount(key[0] + "To" + key[key.length - 1]);
    }

    /**
     * 取多个key的否集并计算总数
     *
     * @param key key
     * @return 总数
     */
    public static Long OpNotCount(String... key) {
        byte[][] keys = new byte[key.length][];
        for (int i = 0; i < key.length; i++) {
            keys[i] = key[i].getBytes();
        }
        redisTemplate.execute((RedisCallback) con -> con.bitOp(RedisStringCommands.BitOperation.NOT, (key[0] + "To" + key[key.length - 1]).getBytes(), keys));
        redisTemplate.expire(key[0] + "To" + key[key.length - 1], 10, TimeUnit.SECONDS);
        return RedisBitMapUtils.bitCount(key[0] + "To" + key[key.length - 1]);
    }
}
