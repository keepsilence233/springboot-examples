package cn.wengzi.test;

import cn.wengzi.Application;
import cn.wengzi.model.Twitter;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author leizige
 * Elasticsearch 7.7.0 高级客户端测试api
 */
@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ElasticSearchDocumentTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private Gson gson;

    /**
     * 索引名称
     */
    private final String index = "twitter";

    /**
     * 创建索引并添加文档信息
     */
    @Test
    public void createDocument() {
        /* 创建对象 */
        Twitter twitter = new Twitter("kimchy", new Date(), "Trying out Elasticsearch, so far so good?");
        /* 创建请求 */
        IndexRequest request = new IndexRequest(index);

        /* PUT twitter/_doc/id */
        request.id(UUID.randomUUID().toString())
                .timeout(TimeValue.timeValueSeconds(2))
                .source(gson.toJson(twitter), XContentType.JSON);
        IndexResponse index = null;
        try {
            index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(restHighLevelClient);
        }
        assert index != null;
        System.err.println(index.toString());
        System.err.println(index.status());
    }

    /**
     * 判断文档是否存在,存在取出文档信息
     */
    @Test
    public void existsDocument() {
        GetRequest request = new GetRequest(index, "38f4240d-6a23-4158-b8f2-fbf556a227ad");
        /* 不获取返回_source的上下文 */
//        request.fetchSourceContext(new FetchSourceContext(false));
        boolean result = false;
        try {
            /* GET twitter/_doc/id */
            result = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(restHighLevelClient);
        }
        System.err.println(result);
        if (result) {
            /* 文档存在,获取文档信息 */
            GetResponse response = null;
            try {
                response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(restHighLevelClient);
            }
            assert response != null;
            System.err.println("version : " + response.getVersion());
            System.err.println("source : " + response.getSourceAsString());
        }
    }

    /**
     * 更新文档信息
     */
    @Test
    public void updateDocument() {
        UpdateRequest request = new UpdateRequest(index, "38f4240d-6a23-4158-b8f2-fbf556a227ad");
        request.timeout(TimeValue.timeValueSeconds(2));

        /* 待更新的信息 */
        Twitter twitter = new Twitter("elastic", new Date(), "Building the site, should be kewl");
        request.doc(gson.toJson(twitter), XContentType.JSON);

        UpdateResponse response = null;
        try {
            response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(restHighLevelClient);
        }
        assert response != null;
        System.err.println(response.toString());
        System.err.println(response.status());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() {
        DeleteRequest request = new DeleteRequest(index, "38f4240d-6a23-4158-b8f2-fbf556a227ad");
        request.timeout(TimeValue.timeValueSeconds(2));
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(restHighLevelClient);
        }
        assert response != null;
        System.err.println(response.status());
    }

    /**
     * bulk Api批量添加文档
     */
    @Test
    public void batchCreateDocument() {
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueSeconds(10));

        List<Twitter> twitters = new ArrayList<Twitter>();

        Twitter twitter1 = new Twitter("kimchy", new Date(), "Trying out Elasticsearch, so far so good?");
        Twitter twitter2 = new Twitter("kimchy", new Date(), "Another tweet, will it be indexed?");
        Twitter twitter3 = new Twitter("elastic", new Date(), "Building the site, should be kewl");
        twitters.add(twitter1);
        twitters.add(twitter2);
        twitters.add(twitter3);

        twitters.forEach(twitter -> {
            request.add(new IndexRequest(index).source(gson.toJson(twitter), XContentType.JSON));
        });
        BulkResponse response = null;
        try {
            response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(restHighLevelClient);
        }
        assert response != null;
        /* 是否失败,返回false代表成功 */
        System.err.println(response.hasFailures());
    }

    /**
     * 批量获取文档信息
     */
    @Test
    public void multiGetDocument() {
        MultiGetRequest request = new MultiGetRequest();
        request.add(new MultiGetRequest.Item(index, "Kz44snIBAgICBGdsxv-z"));
        request.add(new MultiGetRequest.Item(index, "LD44snIBAgICBGdsxv-z"));
        request.add(new MultiGetRequest.Item(index, "LT44snIBAgICBGdsxv-z"));
        MultiGetResponse response = null;
        try {
            response = restHighLevelClient.mget(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Twitter> twitters = new ArrayList<>();
        assert response != null;
        for (MultiGetItemResponse next : response) {
            String source = next.getResponse().getSourceAsString();
            twitters.add(gson.fromJson(source, Twitter.class));
        }
        System.err.println(twitters);
    }

    /**
     * 通过条件查询
     */
    @Test
    public void searchDocument() {
        SearchRequest request = new SearchRequest(index);
        /* 构建查询条件 */
        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        /* 精确查询 */
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("user", "elastic");
        searchBuilder.query(termQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("name");
        searchBuilder.highlighter(highlightBuilder);

        request.source(searchBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert searchResponse != null;
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.err.println(hit.getSourceAsMap());
        }

    }

    private void close(RestHighLevelClient restHighLevelClient) {
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
