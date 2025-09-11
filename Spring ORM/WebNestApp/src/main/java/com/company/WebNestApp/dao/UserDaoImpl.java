package com.company.WebNestApp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.WebNestApp.model.User;

public class UserDaoImpl implements UserDao {
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
    	sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public List<User> getUsers() {
        return getSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }
    
    @Override
    public User findByName(String name) {
    	return getSession()
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", name)
                .uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        return getSession().get(User.class, email);
    }

    @Override
    public User validateUser(String email, String password) {
        String hql = "FROM User WHERE email = :email AND password = :password";
        return getSession()
                .createQuery(hql, User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public void deleteUser(String email) {
        User user = findByEmail(email);
        if (user != null) {
            getSession().delete(user);
        }
    }
}
