package org.example.mongo.document;

import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateDocumentService {

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 更新集合中匹配查询到的第一条文档数据，如果没有找到就创建并插入一个新的文档
     * <p>
     * 创建条件对象，并添加到查询对象中
     * Criteria criteria = Criteria.where("age").is(30);
     * Query query = new Query(criteria);
     * 创建更新对象，并设置更新的内容
     * Update update = new Update().set("age", 33).set("name", "张三");
     * </p>
     *
     * @return 更新后的结果
     */
    public UpdateResult upsert(Query query, Update update, Class<?> clazz, String collectionName) {
        return mongoTemplate.upsert(query, update, clazz, collectionName);
    }


    /**
     * 更新集合中匹配查询到的文档第一条数据
     * <p>
     * 创建条件对象，并添加到查询对象中
     * Criteria criteria = Criteria.where("age").is(30);
     * Query query = new Query(criteria);
     * 创建更新对象，并设置更新的内容
     * Update update = new Update().set("age", 33).set("name", "张三");
     * </p>
     *
     * @return 更新后的结果
     */
    public UpdateResult updateFirst(Query query, Update update, Class<?> clazz, String collectionName) {
        return mongoTemplate.updateFirst(query, update, clazz, collectionName);
    }


    /**
     * 更新匹配查询搭配文档数据中的所有数据
     * <p>
     * 创建条件对象，并添加到查询对象中
     * Criteria criteria = Criteria.where("age").is(30);
     * Query query = new Query(criteria);
     * 创建更新对象，并设置更新的内容
     * Update update = new Update().set("age", 33).set("name", "张三");
     * </p>
     *
     * @return 更新后的结果
     */
    public UpdateResult updateMany(Query query, Update update, Class<?> clazz, String collectionName) {
        return mongoTemplate.updateMulti(query, update, clazz, collectionName);
    }

}
