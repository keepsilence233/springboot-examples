package org.example.mongo.collection;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RemoveCollectionService {


    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 删除集合
     *
     * @return 是否删除成功
     */
    public boolean dropCollection(String collectionName) {
        mongoTemplate.getCollection(collectionName).drop();
        return mongoTemplate.collectionExists(collectionName);
    }
}
