package com.company.HibernateORM;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.HibernateORM.Util.HibernateUtil;
import com.company.HibernateORM.model.Student;



/**
 * Hello world!
 *
 */

public class App {
	@SuppressWarnings("removal")
	public static void main( String[] args )
    {
		// to get the session from sessionfactory (It can be multiple session based on multiple services or work)
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		// To create a  transaction ( Transient + Persistent) -- Transient when you do not to reflect and save in backend  but persistent to save into a backend
	   Transaction tx = session.beginTransaction();
	   
	   Student s1 = new Student("hibernate","yash@gmail" , "Yash");
	   
		session.persist(s1);
		
		Student data = session.get(Student.class,s1.getId());
		System.out.println("Data id : " + data);
		
    }
}
