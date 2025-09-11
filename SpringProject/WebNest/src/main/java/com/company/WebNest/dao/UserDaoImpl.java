package com.company.WebNest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.WebNest.model.User;

import jakarta.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
    	sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return getSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }
    
    @Override
    @Transactional
    public User findByName(String name) {
    	return getSession()
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", name)
                .uniqueResult();
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return getSession().get(User.class, email);
    }

    @Override
    @Transactional
    public User validateUser(String email, String password) {
        String hql = "FROM User WHERE email = :email AND password = :password";
        return getSession()
                .createQuery(hql, User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user = findByEmail(email);
        if (user != null) {
            getSession().delete(user);
        }
    }
    
    @Override
    @Transactional
    public User findById(long id) {
        return getSession().get(User.class, id);
    }
    
    @Override
    @Transactional
    public void deleteUserById(Long id) {
        User user = findById(id);
        if (user != null) {
            getSession().delete(user);
        }
    }

}
