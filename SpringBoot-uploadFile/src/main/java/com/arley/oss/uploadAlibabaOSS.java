package com.arley.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author wengzi
 * @date 2019/10/13 16:56
 * @Description 上传图片到Alibaba OSS
 */
@Controller
@RequestMapping("/alibabaoss")
public class uploadAlibabaOSS {


    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("filename") MultipartFile file) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FfhTTQdffXd8EUwuLer";
        String accessKeySecret = "yJwt45g57XYysAfX9YtoWvSIleDHjr";
        //存储空间名
        String bucketName = "nihaoxiong";
        //上传文件名
        String objectName = "";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));


        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）
        String uuid = UUID.randomUUID().toString();
        objectName = uuid + suffix;
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(file.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @throws IOException
     * @Description 未实现
     */
    @GetMapping("/downloadFile")
    public void downloadFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FfhTTQdffXd8EUwuLer";
        String accessKeySecret = "yJwt45g57XYysAfX9YtoWvSIleDHjr";
        //存储空间名
        String bucketName = "nihaoxiong";
        //上传文件名
        String objectName = "883b2166-ac14-470a-bb2f-f7a9b663f1db.gif";


    }

    @GetMapping("/delFile")
    public void delFile() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FfhTTQdffXd8EUwuLer";
        String accessKeySecret = "yJwt45g57XYysAfX9YtoWvSIleDHjr";
        //存储空间名
        String bucketName = "nihaoxiong";
        //上传文件名
        String objectName = "1528f0fb-eade-4b5e-a8f2-9fffa90491f9.jpg";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
