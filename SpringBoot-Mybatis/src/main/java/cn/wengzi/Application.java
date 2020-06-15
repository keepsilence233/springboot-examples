package cn.wengzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wengzi
 * @date 2019/11/5 night 22:04
 */
@SpringBootApplication
@MapperScan(value = "cn.wengzi.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
