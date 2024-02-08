package org.example.mongo.collection;


import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.validation.Validator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CreateCollectionsService {

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 创建集合,没有大小限制（默认集合创建方式）
     *
     * @return 是否创建成功
     */
    public boolean createCollection(String collectionName) {
        // 创建集合并返回集合信息
        mongoTemplate.createCollection(collectionName);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(collectionName);
    }


    /**
     * 创建集合（固定大小集合）
     * 创建集合并设置 `capped=true` 创建 `固定大小集合`，可以配置参数 `size` 限制文档大小，可以配置参数 `max` 限制集合文档数量。
     *
     * @return 是否创建成功
     */
    public boolean createCollectionFixedSize(String collectionName) {
        //设置集合参数
        long size = 1024L;
        long max = 5L;
        //创建固定大小集合
        // 创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。
        CollectionOptions collectionOptions = CollectionOptions.empty()
                .capped()
                .size(size)
                .maxDocuments(max); //文档最大数量
        mongoTemplate.createCollection(collectionName,collectionOptions);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(collectionName);
    }

    /**
     * 创建集合（验证文档数据）
     * 创建集合并在文档插入与更新时进行数据校验，如果符合创建集合设置的条件就允许更新与插入，否则则按照设置的策略进行
     *
     * @param collectionName 集合名称
     * @param condition 验证条件
     * @return 是否创建成功
     */
    public boolean createCollectionValidation(String collectionName, Criteria condition) {
        //设置验证条件，只允许年龄大雨20的用户信息插入
//        Criteria condition = Criteria.where("age").gt(20);
        //设置集合选项验证对象
        CollectionOptions collectionOptions = CollectionOptions.empty()
                .validator(Validator.criteria(condition))
                .strictValidation() //校验级别
                .failOnValidationError(); //校验不通过后执行的动作
        mongoTemplate.createCollection(collectionName,collectionOptions);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(collectionName);
    }
}
