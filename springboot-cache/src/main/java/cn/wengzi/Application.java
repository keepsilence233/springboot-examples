package cn.wengzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Arley
 * @email crazyArley@gmail.com
 * @data 2020/03/02 23:03
 * desc: @EnableCaching 开启缓存功能
 */
@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
