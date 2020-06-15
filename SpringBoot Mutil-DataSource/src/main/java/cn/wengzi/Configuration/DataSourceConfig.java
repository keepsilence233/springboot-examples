package cn.wengzi.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 我们禁掉了自动数据源配置，因些需要手动创建数据源
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "PrimaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")// application.yml中对应属性的前缀
    public DataSource PrimaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SecondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")// application.yml中对应属性的前缀
    public DataSource SecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
