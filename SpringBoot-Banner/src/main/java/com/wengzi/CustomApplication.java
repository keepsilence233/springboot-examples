package com.wengzi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * created wengzi 19/8/8
 * 自定义SpringApplication
 */
@SpringBootApplication
public class CustomApplication {
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(CustomApplication.class);
        application.setBannerMode(Banner.Mode.LOG); //是否显示横幅
        application.run(args);
    }
}
