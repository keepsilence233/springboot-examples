package com.arley.oss.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Arley
 */
@Slf4j
@Data
@Component(value = "ossProperties")
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {
    /**
     * OSS域名
     */
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    /**
     * 储存空间名
     */
    private String bucketName;

    @PostConstruct
    public void init() {
        log.info(description());
    }

    public String description() {
        StringBuilder sb = new StringBuilder("\n ossConfigs{");
        sb.append("oss 域名 :").append(endpoint).append("\n");
        sb.append(", oss accessKeyId :").append(accessKeyId).append("\n");
        sb.append(", oss accessKeySecret :").append(accessKeySecret).append("\n");
        sb.append(", oss 存储空间名 :").append(bucketName);
        sb.append("}");
        return sb.toString();
    }
}

