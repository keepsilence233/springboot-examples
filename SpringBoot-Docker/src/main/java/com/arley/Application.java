package com.arley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arley
 * data:2020/1/30
 * desc:Docker中部署springboot项目
 */
@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--server.port=8888");
    }

    @GetMapping("/hello")
    public String Hello() {
        return "Hello Docker";
    }
}
