package cn.wengzi.test;


import cn.wengzi.Application;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * @author leizige
 * Elasticsearch 7.7.0 高级客户端测试api
 */
@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ElasticSearchIndexTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 索引名称
     */
    private final String index = "twitter";

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("创建索引失败:{}", e.toString());
            e.printStackTrace();
        }
        assert createIndexResponse != null;
        System.err.println(createIndexResponse.toString());
    }

    /**
     * 索引是否存在
     */
    @Test
    public void existsIndex() {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean result = false;
        try {
            result = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println(result);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse acknowledgedResponse = null;
        try {
            acknowledgedResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert acknowledgedResponse != null;
        System.err.println(acknowledgedResponse.isAcknowledged());
    }
}


