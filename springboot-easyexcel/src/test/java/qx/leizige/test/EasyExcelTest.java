package qx.leizige.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import qx.leizige.excel.MergeUploadDataListener;
import qx.leizige.test.util.TestFileUtil;

import java.io.File;

/**
 * @author leizige
 */
@SpringBootTest(classes = Application.class)
public class EasyExcelTest {


    @Test
    public void read() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        EasyExcel.read(fileName, new MergeUploadDataListener()).extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();
    }
}
