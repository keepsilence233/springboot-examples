package com.datajpa.repository;


import com.datajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 *  JpaRepository<Student,Integer>
 *      Student:实体类
 *      Integer:主键类型
 */
@Component
public interface StudentRepository extends JpaRepository<Student,Integer> {
    /*
     * 我们在这里直接继承 JpaRepository
     * 这里面已经有很多现场的方法了
     * 这也是JPA的一大优点
     * */
}
