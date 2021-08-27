package qx.leizige.handler;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import qx.leizige.excel.MergeUploadDataListener;
import qx.leizige.module.UploadData;

import java.io.IOException;

@RestController
@RequestMapping(value = "/excel")
public class TestHandler {


    @PostMapping(value = "/upload")
    public void upload(@RequestParam MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), UploadData.class, new MergeUploadDataListener()).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
