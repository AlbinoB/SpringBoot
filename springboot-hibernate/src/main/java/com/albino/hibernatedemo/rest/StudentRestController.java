package com.albino.hibernatedemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albino.hibernatedemo.entity.Student;
import com.albino.hibernatedemo.exceptions.StudentNotFoundException;
import com.albino.hibernatedemo.service.StudentService;

@RestController
@RequestMapping("/api/deprecated")
public class StudentRestController {
    
    private StudentService studentService;

    public StudentRestController(){}

    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService= studentService;
    }


    @GetMapping("/students")
    public List<Student> getAllStudents() {
		return studentService.findAll();
	}
    
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);
        if (student != null)
             return student;
        else 
            throw new StudentNotFoundException("Student with id:"+ studentId +" not found!");
    }

    @PostMapping ("/students")
    public Student addStudent(@RequestBody Student student){
        studentService.save(student);
        return student;
    }

    @PutMapping ("/students")
    public Student updateStudent(@RequestBody Student student){
        studentService.update(student);
        return student;
    }


    @DeleteMapping("/students")
    public List<Student> deleteAllStudents(){
        studentService.deleteAll();
        return new ArrayList<Student>(0);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId){
        studentService.deleteById(studentId);
    }

}
