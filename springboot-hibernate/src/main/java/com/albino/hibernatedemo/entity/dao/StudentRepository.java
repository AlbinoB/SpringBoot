package com.albino.hibernatedemo.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.albino.hibernatedemo.entity.Student;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByLastName(String lastName);
    
}
