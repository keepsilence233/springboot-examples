package com.arley.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;


@Controller
@RequestMapping("/img")
public class uploadHandler {

    @Value("${uploadDir}")
    private String uploadDir;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleUpload(@RequestParam("singleUpload") MultipartFile file) {
        String sufFileName = fileName.substring(fileName.lastIndexOf(".")); //文件后缀名

        String filePath = uploadDir;    //文件上传位置

        fileName = UUID.randomUUID() + sufFileName;     //UUID
        //fileName = new Date().getTime()+sufFileName;  //日期

        File dest = new File(filePath + fileName);
        //如果路径不存在,则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);  //上传
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }


    @Value("${excel.fileName}")
    private String fileName;

    @Value("${excel.filePath}")
    private String filePath;

    @GetMapping("/downloadLocal")
    public void downloadLocal(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        InputStream inStream = new FileInputStream(filePath + fileName);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName + "");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
