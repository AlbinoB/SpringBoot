package com.albino.hibernatedemo.entity.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.albino.hibernatedemo.entity.Student;
import com.albino.hibernatedemo.exceptions.StudentNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findStudentById(int studentId) {
        return entityManager.find(Student.class,studentId);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
        query.setParameter("lastName",lastName);
        return query.getResultList();
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Student deleteStudent(int  studentId) {
        Student student= findStudentById(studentId);
        if(student == null){
            throw new StudentNotFoundException("Student with id:" + studentId + " not found!!!");
        }
        entityManager.remove(student);

        student.setId(-1);

        return student;
    }

    @Override
    public int deleteAllStudents() {
        return entityManager
                        .createQuery("DELETE FROM Student")
                        .executeUpdate();
    }

    
}
