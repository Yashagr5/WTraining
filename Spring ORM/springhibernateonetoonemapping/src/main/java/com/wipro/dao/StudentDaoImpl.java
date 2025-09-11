package com.wipro.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.wipro.entity.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {

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
    @Transactional(readOnly = true)
    public Student getStudent(Long id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }
}
