package com.wipro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.entity.User;

@Configuration
@Transactional
@Repository
public class UserDao {
	@Autowired
	private SessionFactory factory;
	public void saveUser(User obj)
	{
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		session.persist(obj);
//		tx.commit();
//		session.close();
		factory.getCurrentSession().persist(obj);
				
	}
	
	public List<User> getUsers() {
//		String selectQuery="select * from User";
//		List<User> todo=jdbcTemplate.query(selectQuery,new RowMapperImpl());
//		return todo;
		return factory.getCurrentSession().createQuery("from User", User.class).list();
	}
}
