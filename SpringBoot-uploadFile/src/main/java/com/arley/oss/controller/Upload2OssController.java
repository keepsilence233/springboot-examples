package com.arley.oss.controller;

import com.arley.oss.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 *
 * @author leizige
 */
@Slf4j
@RestController
@RequestMapping("/upload2oss")
@SuppressWarnings("unchecked")
public class Upload2OssController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) {
        String result = null;
        try {
            result = uploadService.uploadObject2OSS(file);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传出错", e);
        }
        return result;
    }
}
