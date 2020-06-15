package cn.wengzi.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = -3539648771073621352L;
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
}
