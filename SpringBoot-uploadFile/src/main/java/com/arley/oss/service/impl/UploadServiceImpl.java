package com.arley.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.arley.oss.service.UploadService;
import com.arley.oss.util.OSSProperties;
import com.arley.oss.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.UUID;

@Slf4j
@Service("uploadService")
@SuppressWarnings("all")
public class UploadServiceImpl implements UploadService {


    @Autowired
    private OSS oss;

    @Autowired
    @Qualifier("ossProperties")
    private OSSProperties properties;

    @Override
    public String uploadObject2OSS(MultipartFile file) throws IOException {
        String url = null;

        CommonsMultipartFile multipartFile = (CommonsMultipartFile) file;
        FileItem fileItem = multipartFile.getFileItem();
        InputStream inputStream = fileItem.getInputStream();

        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        ObjectMetadata metadata = new ObjectMetadata();
        //上传的文件的长度
        metadata.setContentLength(inputStream.available());
        //指定该Object被下载时的网页的缓存行为
        metadata.setCacheControl("no-cache");
        //指定该Object下设置Header
        metadata.setHeader("Pragma", "no-cache");
        //指定该Object被下载时的内容编码格式
        metadata.setContentEncoding("utf-8");
        //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
        //如果没有扩展名则填默认值application/octet-stream
        metadata.setContentType(OSSUtil.getContentType(fileName));
        //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
        metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
        // 文件名使用唯一的UUID 上传文件(上传文件流的形式)
        fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        oss.putObject(properties.getBucketName(), fileName, new ByteArrayInputStream(file.getBytes()), metadata);
        //解析结果
        try {
            url = OSSUtil.getUrl(oss, fileName, properties);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return url;
    }
}
