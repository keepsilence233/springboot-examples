package cn.wengzi.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
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


    /**
     * 无密码调用方式
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(elasticSearchProperties.getAddress(),
                                elasticSearchProperties.getPort(), elasticSearchProperties.getScheme())
                )
        );
    }

    /**
     * 有密码调用方式
     *
     * @return
     */
    @Bean
    public RestHighLevelClient esRestClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elasticsearch", "bitsun@es"));
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(elasticSearchProperties.getAddress(),
                                elasticSearchProperties.getPort(), elasticSearchProperties.getScheme())
                ).setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        );
    }

}