package com.wengzi;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * creater wengzi 19/8/8
 */
@SpringBootApplication
public class ExitCodeApplication {
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

    public static void main(String[] args) {
        //Spring Boot返回实现getExitCode()方法提供的退出代码
        System.exit(SpringApplication.exit(SpringApplication.run(ExitCodeApplication.class, args)));


    }
}
