package cn.wengzi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * twitter索引字段信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Twitter implements Serializable {
    private String user;
    private Date postDate;
    private String message;
}
