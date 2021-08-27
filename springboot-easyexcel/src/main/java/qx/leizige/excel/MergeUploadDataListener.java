package qx.leizige.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qx.leizige.module.UploadData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeUploadDataListener extends AnalysisEventListener<Map<Integer, String>> {

    private static final Logger log = LoggerFactory.getLogger(MergeUploadDataListener.class);

    /**
     * Map<rowIndex,Map<columnIndex,value>>
     */
    private final Map<Integer, Map<Integer, String>> uploadDataMap = new HashMap<>();

    /**
     * 合并单元格
     */
    private final List<CellExtra> extraMergeInfoList = new ArrayList<>();


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        //读取到的每行数据,其key是以0开始的索引
        uploadDataMap.put(context.readRowHolder().getRowIndex(), data);
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        log.info("读取到了一条额外信息:{}", JSON.toJSONString(extra));
        switch (extra.getType()) {
            case COMMENT:
            case HYPERLINK:
                break;
            case MERGE:
                extraMergeInfoList.add(extra);
                break;
            default:
                log.info("Unknown type : {}", extra.getType());
        }
    }

    /**
     * 处理合并单元格
     *
     * @param data               解析数据
     * @param extraMergeInfoList 合并单元格信息
     * @return 填充好的解析数据
     */
    private void explainMergeData(Map<Integer, Map<Integer, String>> data, List<CellExtra> extraMergeInfoList) {
        //循环所有合并单元格信息
        extraMergeInfoList.forEach(cellExtra -> {
            int firstRowIndex = cellExtra.getFirstRowIndex();
            int lastRowIndex = cellExtra.getLastRowIndex();
            int firstColumnIndex = cellExtra.getFirstColumnIndex();
            int lastColumnIndex = cellExtra.getLastColumnIndex();

            Map<Integer, String> rdata = data.get(cellExtra.getRowIndex());
            String val = null;
            if (rdata != null) {
                val = rdata.get(cellExtra.getColumnIndex());
            }
            //遍历每行
            Map<Integer, String> rowData = null;
            for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                rowData = data.get(i);
                if (rowData == null) {
                    continue;
                }
                for (int c = firstColumnIndex; c <= lastColumnIndex; c++) {
                    rowData.put(c, val);
                }
            }
        });
    }

    private List<UploadData> convertUploadList(Map<Integer, Map<Integer, String>> uploadDataMap) {
        final List<UploadData> uploadDataList = new ArrayList<>();
        uploadDataMap.forEach((key, value) -> {
            List<String> values = new ArrayList<>(value.values());
            UploadData uploadData = new UploadData();
            uploadData.setDistrict(values.get(0));
            uploadData.setProvinceName(values.get(1));
            uploadDataList.add(uploadData);
        });
        return uploadDataList;
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //所有行都解析完成
        this.explainMergeData(uploadDataMap, extraMergeInfoList);
        List<UploadData> uploadDataList = convertUploadList(uploadDataMap);
        System.out.println(JSON.toJSONString(uploadDataList, true));
    }
}
