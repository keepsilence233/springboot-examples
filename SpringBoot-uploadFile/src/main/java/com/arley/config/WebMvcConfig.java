package com.arley.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot设置虚拟路径映射绝对路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 文件磁盘图片url 映射
     * 配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //有的版本有的不需要,有的版本需要file
        registry.addResourceHandler("/image/**").addResourceLocations("file:F:\\upload\\");
    }
}
