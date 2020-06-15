package cn.wengzi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengzi
 * @description 监控数据源
 */
@Configuration
public class DruidConfiguration {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /***
     * ServletRegistrationBean提供类的进行注册
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        //设置初始化参数
        Map<String, String> initMap = new HashMap<String, String>(16);

        //初始化参数 白名单
        initMap.put("allow", "127.0.0.1");
        //黑名单
        initMap.put("deny", "192.168.1.73");

        //登陆查看信息的账户名和密码 http://localhost:8888/druid/login.html
        initMap.put("loginUsername", "admin");
        initMap.put("loginPassword", "123456");

        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        //添加一个过滤规则
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        //设置初始化参数
        Map<String, String> initMap = new HashMap<String, String>(16);
        //添加不需要过滤的格式信息
        initMap.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }
}
