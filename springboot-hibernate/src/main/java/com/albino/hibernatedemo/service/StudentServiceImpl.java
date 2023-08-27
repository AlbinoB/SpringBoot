package com.albino.hibernatedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albino.hibernatedemo.entity.Student;
import com.albino.hibernatedemo.entity.dao.StudentRepository;
import com.albino.hibernatedemo.exceptions.StudentNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer studentId) {
        Optional<Student> sOptional= studentRepository.findById(studentId);
        
        if (sOptional.isPresent()) {
            return sOptional.get();
        } else {
            throw new StudentNotFoundException("Student with id:" + studentId + " not found!!!");
        }
        
    }
    
    
    @Override
    public List<Student> findStudentByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAll() {
         studentRepository.deleteAll();
    }

    
    
}
