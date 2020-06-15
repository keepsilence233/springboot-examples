package com.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "student")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    private Long id;
    private String name;
    private Address address;
    private Map<String,Object> location;
    private List<String> skills;
    private Set<String>  skills2;
    @Value("${name2}")
    private String name2;
}
