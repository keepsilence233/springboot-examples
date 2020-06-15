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
 * SecondaryDataSource:从数据库
 */
@Configuration
@MapperScan(basePackages = {"cn.wengzi.mapper"}, sqlSessionFactoryRef = "SecondarySqlSessionFactory")
public class SecondaryDataBaseConfig {

    @Autowired
    @Qualifier("SecondaryDataSource")
    private DataSource secondaryDataSource;

    @Bean
    public SqlSessionFactory SecondarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondaryDataSource); //使用Secondary数据源，连接test2数据库
        //设置从数据库 mapper.xml位置
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/SecondaryMapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate SecondarySqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(SecondarySqlSessionFactory()); //使用上面配置的Factory
        return template;
    }
}
