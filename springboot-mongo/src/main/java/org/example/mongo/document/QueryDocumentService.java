package org.example.mongo.document;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QueryDocumentService {

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 查询集合中全部文档信息
     *
     * @return 集合中全部文档信息
     */
    public List findAll(Class<?> clazz, String collectionName) {
        return mongoTemplate.findAll(clazz, collectionName);
    }

    /**
     * 根据文档id查询集合中文档数据
     *
     * @return 文档信息
     */
    public Object findById(Class<?> clazz, String id, String collectionName) {
        return mongoTemplate.findById(id, clazz, collectionName);
    }


    /**
     * 根据条件查询集合中符合条件的文档（只取第一条）
     * <p>
     * int age = 22;
     * Criteria criteria = Criteria.where("age").is(age);
     * Query query = new Query(criteria);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的第一条文档
     */
    public Object findOne(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.findOne(query, clazz, collectionName);
    }

    /**
     * 根据条件查询集合中符合条件的文档，获取其文档列表
     * <p>
     * int age = 22;
     * Criteria criteria = Criteria.where("age").is(age);
     * Query query = new Query(criteria);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByCondition(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }


    /**
     * 根据条件查询集合中符合条件的文档，获取其文档列表并排序
     * <p>
     * int age = 22;
     * Criteria criteria = Criteria.where("age").is(age);
     * Query query = new Query(criteria).with(Sort.by("age"));
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByConditionAndSort(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }

    /**
     * 根据条件查询集合中符合条件的文档，获取其文档列表并排序,限制返回条数
     * <p>
     * int age = 22;
     * Criteria criteria = Criteria.where("age").is(age);
     * Query query = new Query(criteria).with(Sort.by("age")).limit(2);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByConditionAndSortLimit(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }

    /**
     * 根据条件查询集合中符合条件的文档，获取其文档列表并排序,限制返回条数
     * <p>
     * int age = 22;
     * Criteria criteria = Criteria.where("age").is(age);
     * Query query = new Query(criteria).with(Sort.by("age")).skip(2);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByConditionAndSortSkip(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }


    /**
     * 查询存在指定字段名称的文档数据
     * <p>
     * String field = "age";
     * Criteria criteria = Criteria.where(field).exists(true);
     * Query query = new Query(criteria);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByExistsField(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }


    /**
     * 根据【and】关联多个查询条件，查询集合中的文档数据
     * <p>
     * String sex = "男";
     * Integer age = 22;
     * Criteria criteriaSex = Criteria.where("sex").is(ses);
     * Criteria criteriaAge = Criteria.where("age").is(age);
     * 创建条件对象，将上面条件进行 AND 关联
     * Criteria criteria = new Criteria().andOperator(criteriaSex, criteriaAge);
     * Query query = new Query(criteria);
     * </p>
     *
     * @param query          查询条件
     * @param collectionName 集合名称
     * @return 符合条件的文档列表
     */
    public List findByAndCondition(Class<?> clazz, Query query, String collectionName) {
        return mongoTemplate.find(query, clazz, collectionName);
    }
}

