package org.example.mongo.document;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RemoveDocumentService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 删除集合中符合条件的一个或多个文档
     * <p>
     * 创建条件对象
     * Criteria criteria = Criteria.where("age").is(“张三”).and("sex").is(20);
     * 创建查询对象，然后将条件对象添加到其中
     * Query query = new Query(criteria);
     * </p>
     *
     * @return 删除文档后的结果
     */
    public Object remove(Query query, String collectionName) {
        return mongoTemplate.remove(query, collectionName);
    }


    /**
     * 删除符合条件的单个文档，并返回删除的文档
     * <p>
     * 创建条件对象
     * Criteria criteria = Criteria.where("name").is("张三");
     * 创建查询对象，然后将条件对象添加到其中
     * Query query = new Query(criteria);
     * </p>
     *
     * @return 删除的文档信息
     */
    public Object findAndRemove(Query query, Class<?> clazz, String collectionName) {
        return mongoTemplate.findAndRemove(query, clazz, collectionName);
    }

    /**
     * 删除符合条件的全部文档，并返回删除的文档
     * <p>
     * 创建条件对象
     * Criteria criteria = Criteria.where("name").is("张三");
     * 创建查询对象，然后将条件对象添加到其中
     * Query query = new Query(criteria);
     * </p>
     *
     * @return 删除的文档信息
     */
    public Object findAllAndRemove(Query query, Class<?> clazz, String collectionName) {
        return mongoTemplate.findAllAndRemove(query, clazz, collectionName);
    }
}
