package com.datajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @JsonIgnoreProperties(value={"hibernateLazyInitializer"})
 * 这行代码的作用在于告诉你的jsonPlug组件，在将你的代理对象转换为json对象时，忽略value对应的数组中的属性，即：
 * 通过java的反射机制将pojo转换成json的，属性，(通过java的反射机制将pojo转换成json的，)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity  // 该注解声明一个实体类，与数据库中的表对应
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Student {
    @Id  // 表明id
    private Integer id;
    private String name;
    private Integer age;


}
