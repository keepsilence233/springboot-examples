package qx.leizige.redis.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseTest.Application.class)
public abstract class BaseTest {

    @Import(RedisAutoConfiguration.class)
    @ComponentScan(value = "qx.leizige.redis.test")
    public static class Application {

    }

    @Configuration
    @ConditionalOnClass(RedisAutoConfiguration.class)
    public static class RedisConfig {

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            // 使用Jackson2JsonRedisSerialize 替换默认序列化
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

            redisTemplate.setConnectionFactory(redisConnectionFactory);
            // 设置value的序列化规则和 key的序列化规则
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
            redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setEnableDefaultSerializer(true);
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }

        @Bean
        public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
            return redisTemplate.opsForHash();
        }

        @Bean
        public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
            return redisTemplate.opsForValue();
        }

        @Bean
        public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
            return redisTemplate.opsForList();
        }

        @Bean
        public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
            return redisTemplate.opsForSet();
        }

        @Bean
        public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
            return redisTemplate.opsForZSet();
        }
    }
}

