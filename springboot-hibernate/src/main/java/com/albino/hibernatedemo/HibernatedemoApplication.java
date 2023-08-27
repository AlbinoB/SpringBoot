package com.albino.hibernatedemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.albino.hibernatedemo.entity.Student;
import com.albino.hibernatedemo.entity.dao.StudentDAOImpl;
import com.albino.hibernatedemo.service.StudentService;

@SpringBootApplication
public class HibernatedemoApplication {

	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Autowired
	public HibernatedemoApplication(StudentService studentService){
		this.studentService = studentService;
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args){
		return runner->{
			System.out.println("Rest Web Service up...");
			
			
			createStudent(new Student("Jelton","Periera","abc@gmail.com"));

			createStudent(new Student("Albino","Braganza","abc@gmail.com"));
			
			
		};
	}

	private void createStudent(Student student) {
		studentService.save(student);
	}




}
