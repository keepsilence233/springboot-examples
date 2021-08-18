package cn.wengzi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author wengzi
 * @date 2019/10/16 晚上23:31
 * @Description Swagger2配置类
 * @Configuration Spring来加载该类配置
 * @EnableSwagger2 启用Swagger2
 */
@Configuration
public class Swagger2Config {


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
                // 指定要生成api接口的包路径
                .apis(RequestHandlerSelectors.basePackage("cn.wengzi.controller"))
                //使用了 @ApiOperation 注解的方法生成api接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
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
                //设置作者及联系方式,可有可无
                .contact("qx_leizige@outlook.com")
                //描述
                .description("API描述:https://www.cnblogs.com/leizzige/")
                //版本号
                .version("1.0")
                .build();
    }
}
