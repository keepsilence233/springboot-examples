package org.example.mongo.collection;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class QueryCollectionService {


    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 获取集合列表
     *
     * @return 集合名称列表
     */
    public Set<String> getCollectionNames() {
        return mongoTemplate.getCollectionNames();
    }

    /**
     * 判断集合是否存在
     *
     * @param collectionName 集合名称
     * @return 集合是否存在
     */
    public boolean CollectionExists(String collectionName) {
        if (!StringUtils.isEmpty(collectionName)) {
            return mongoTemplate.collectionExists(collectionName);
        }
        return Boolean.FALSE;
    }

}
