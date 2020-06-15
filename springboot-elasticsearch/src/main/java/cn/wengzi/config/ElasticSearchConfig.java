package cn.wengzi.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leizige
 * ElasticSearch 配置
 */
@Configuration
public class ElasticSearchConfig {

    @Autowired
    private ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(elasticSearchProperties.getAddress(),
                                elasticSearchProperties.getPort(), elasticSearchProperties.getScheme())
                )
        );
    }
}
