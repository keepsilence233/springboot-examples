package cn.wengzi.Configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 配置两个mybatis的SqlSessionFactory分别使用不同的数据源
 *
 * PrimaryDataSource :主数据库
 */
@Configuration
@MapperScan(basePackages = {"cn.wengzi.mapper.primarymapper"}, sqlSessionFactoryRef = "PrimarySqlSessionFactory")
public class PrimaryDataBaseConfig {

    @Autowired
    @Qualifier("PrimaryDataSource")
    private DataSource primaryDataSource;

    @Bean
    public SqlSessionFactory PrimarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(primaryDataSource); //使用Primary数据源，连接test1数据库
        //设置主数据库 mapper.xml位置
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/PrimaryMapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate PrimarySqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(PrimarySqlSessionFactory()); //使用上面配置的Factory
        return template;
    }
}
