package com.springboot;

import com.springboot.entity.Grade;
import com.springboot.entity.Student;
import com.springboot.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    Student student;
    @Autowired
    Grade grade;
    @Autowired
    ApplicationContext context;
    @Test
    public void contextLoads() {
//        StudentRepository studentRepository =  (StudentRepository)context.getBean("studentRepository");
//        studentRepository.studentRepository();
        System.err.println(student);
    }

}
