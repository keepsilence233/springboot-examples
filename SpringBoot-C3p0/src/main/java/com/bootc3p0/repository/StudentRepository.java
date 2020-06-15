package com.bootc3p0.repository;

import com.bootc3p0.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StudentRepository {
    public List<Student> findAll();
    public Student findById(Long id);
}
