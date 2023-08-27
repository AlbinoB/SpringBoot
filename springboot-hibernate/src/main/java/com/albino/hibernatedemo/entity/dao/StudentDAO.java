package com.albino.hibernatedemo.entity.dao;

import java.util.List;

import com.albino.hibernatedemo.entity.Student;


public interface StudentDAO {
    
    void save(Student student);

    Student findStudentById(int studentId);

    List<Student> findAll();

    List<Student> findStudentByLastName(String lastName);

    void update(Student student);

    Student deleteStudent(int studentId);

    int deleteAllStudents();

}
