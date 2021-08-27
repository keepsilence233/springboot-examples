package qx.leizige.module;


import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;


public class UploadData implements Serializable {

    @ExcelProperty(value = "地区")
    private String district;

    @ExcelProperty(value = "省份")
    private String provinceName;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
