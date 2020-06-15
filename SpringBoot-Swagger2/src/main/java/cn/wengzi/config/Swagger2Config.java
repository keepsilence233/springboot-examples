package cn.wengzi.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wengzi
 * @date 2019/10/16 晚上23:31
 * @Description Swagger2配置类
 * @Configuration Spring来加载该类配置
 * @EnableSwagger2 启用Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("config-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /***
     * 通过 createRestApi 函数来构建一个 Docket Bean
     * 接口文档默认访问路径 http://127.0.0.1:8888/swagger-ui.html
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                ////指定扫描添加了@ApiOperation注解的请求
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //对所有路径进行扫描
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置文档信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot中使用Swagger2构建RESTFUL APIs")
                //详细信息
                .description("SpringBoot整合Swagger,详细信息......")
                //设置作者及联系方式,可有可无
                .contact("wengzi")
                //描述
                .description("API描述:https://www.nihaoxiong.cn")
                //版本号
                .version("1.0")
                .build();
    }
}
