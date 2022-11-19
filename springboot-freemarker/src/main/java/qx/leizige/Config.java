package qx.leizige;

import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

@Component
public class Config {

    @Bean
    public Configuration wordConfiguration() {
        Configuration result = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        result.setDefaultEncoding("utf-8");
        //设置模板加载器
        result.setClassForTemplateLoading(this.getClass(), "/template");
        return result;
    }
}
