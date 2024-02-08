package org.example.mongo.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CreateDocumentService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 插入一条文档数据，如果文档信息已存在就抛出一场
     *
     * @param obj            文档数据
     * @param collectionName 集合名称
     * @return 插入后的信息
     */
    public Object insert(Object obj, String collectionName) {
        //插入一条用户数据，如果文档信息已存在就抛出异常
        return mongoTemplate.insert(obj, collectionName);
    }

    /**
     * 插入多条文档数据，如果文档信息已存在则抛出异常
     *
     * @param objList        多条文档信息
     * @param collectionName 集合名称
     * @return 创建后的文档信息
     */
    public Collection<Object> insertMany(List<Object> objList, String collectionName) {
        return mongoTemplate.insert(objList, collectionName);
    }

    /**
     * 存储一条文档信息，如果文档信息已存在就执行更新
     *
     * @param obj            文档数据
     * @param collectionName 集合名称
     * @return 存储的文档信息
     */
    public Object save(Object obj, String collectionName) {
        return mongoTemplate.save(obj, collectionName);
    }
}
