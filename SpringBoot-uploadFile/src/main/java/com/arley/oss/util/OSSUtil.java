package com.arley.oss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

/**
 * oss 工具类
 *
 * @author Arley
 */
@Slf4j
public class OSSUtil {

    /**
     * 创建存储空间
     *
     * @param oss        OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSS oss, String bucketName) {
        //存储空间
        final String bucketNames = bucketName;
        if (!oss.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = oss.createBucket(bucketName);
            log.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param oss        oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSS oss, String bucketName) {
        oss.deleteBucket(bucketName);
        log.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param oss        oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSS oss, String bucketName, String folder) {
        //文件夹名
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建
        if (!oss.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹
            oss.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            log.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = oss.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param oss        oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"qj_nanjing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSS oss, String bucketName, String folder, String key) {
        oss.deleteObject(bucketName, folder + key);
        log.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param fileName
     * @return
     */
    public static String getUrl(OSS oss, String fileName, OSSProperties properties) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = oss.generatePresignedUrl(properties.getBucketName(), fileName, expiration);
        if (url != null) {
            return url.toString();
        }
        log.error("获取URL链接错误 {}", url);
        return null;
    }
}
