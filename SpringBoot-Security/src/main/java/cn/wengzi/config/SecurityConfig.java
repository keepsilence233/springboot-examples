package cn.wengzi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wengzi
 * @date 2019/11/9 wee hours 00:00
 * @description Security 配置类
 * @EnableWebSecurity 使 Security 生效
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 添加账户和角色关系
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPassWordEncoder())
                .withUser("user").password(new MyPassWordEncoder().encode("000")).roles("USER")
                .and()
                .withUser("admin").password(new MyPassWordEncoder().encode("123123")).roles("ADMIN", "USER");
    }

    /**
     * 设置角色和权限的关系
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/index").access("hasRole('ADMIN') or hasRole('USER')")
                .and()
                //自定义登陆页面
                .formLogin().loginPage("/login")
                //登陆请求不进行验证
                .permitAll()
                .and()
                .logout()
                //退出请求不进行验证
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
