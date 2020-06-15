package com.datajpa.service;

import com.datajpa.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student getOne(Integer id);
    Student saveAndFlush(Student student);
    void deleteById(Integer id);
}
