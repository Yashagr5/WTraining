package com.example.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	private final SessionFactory sessionFactory;

    // Constructor injection
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        sessionFactory.getCurrentSession().persist(student);
    }

    @Override
    @Transactional
    public Student getStudent(Long id) {
        Student student = sessionFactory.getCurrentSession().get(Student.class, id);
        Hibernate.initialize(student.getClass());
		return student ;
    }
}
