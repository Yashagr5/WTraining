package com.gl.dao;

import com.gl.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class DaoImpl implements DaoStudent{
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Student student) {
        getSession().save(student);
    }

    @Override
    public Student findById(int id) {
        return getSession().get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return getSession().createQuery("from Student", Student.class).list();
    }

    @Override
    public void update(Student student) {
        getSession().update(student);
    }

	@Override
    public void delete(int id) {
        Student student = findById(id);
        if (student != null) {
            getSession().delete(student);
        }
    }
}
