package cn.wengzi.excel.entity;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Excel 使用在类上，表明这个类要绑定excel
 * 参数	    描述
 * value	Excel文件名，优先级低于方法传入
 * type	    Excel文档类型，默认XLS
 * @ExcelField 使用在字段上，表明这是Excel的列表头
 * 参数	    描述
 * value	列表头名字
 * pattern	如果是时间需要转换指定格式，需要指定
 * width	这个列表头单元格的宽度
 */
@Excel
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @ExcelField("id值")
    private Integer id;
    @ExcelField("用户名")
    private String userName;
    @ExcelField("用户地址")
    private String Address;
    @ExcelField("城市名")
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
