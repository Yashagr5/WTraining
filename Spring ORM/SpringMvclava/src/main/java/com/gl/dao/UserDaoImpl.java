package com.gl.dao;

import com.gl.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
        getSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers() {
        return getSession()
                .createQuery("FROM User", User.class)
                .getResultList();
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
