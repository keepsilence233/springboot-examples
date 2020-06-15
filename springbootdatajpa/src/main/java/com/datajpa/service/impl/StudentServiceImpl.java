package com.datajpa.service.impl;

import com.datajpa.entity.Student;
import com.datajpa.repository.StudentRepository;
import com.datajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        // 这里我们就可以直接使用 findAll 方法
        return studentRepository.findAll();
    }

    public Student getOne(Integer id) {
        return studentRepository.getOne(id);
    }

    public Student saveAndFlush(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
