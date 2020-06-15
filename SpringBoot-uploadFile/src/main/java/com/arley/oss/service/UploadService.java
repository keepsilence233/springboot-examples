package com.arley.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 *
 * @author Arley
 */
public interface UploadService {

    /**
     * 上传文件
     */
    String uploadObject2OSS(MultipartFile file) throws IOException;
}
