package com.albino.hibernatedemo.service;

import java.util.List;

import com.albino.hibernatedemo.entity.Student;

public interface StudentService {
    
    void save(Student student);

    Student findById(Integer studentId);

    List<Student> findAll();

    List<Student> findStudentByLastName(String lastName);

    void update(Student student);

    void deleteById(Integer studentId);

    void deleteAll();

}
