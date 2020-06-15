package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties(value = "grade")
@PropertySource(value = {"classpath:conf.properties"},encoding = "utf-8")
public class Grade {
    private Long gradeId;
    private String gradeName;

}
