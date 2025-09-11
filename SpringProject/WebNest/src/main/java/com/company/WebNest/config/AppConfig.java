package com.company.WebNest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.company.WebNest.model.Comment;
import com.company.WebNest.model.Task;
import com.company.WebNest.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.company.WebNest")
public class AppConfig {
	@Bean
	public DataSource DataSource()
	{
		//Create and configure a 'DriverManagerDataSource' with the database connection details given below
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUrl("jdbc:mysql://localhost:3306/wiprojdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("Yash@123");
		return dataSource;
	}
	
	
	  @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(DataSource());
	        sessionFactory.setAnnotatedClasses(User.class, Task.class, Comment.class);
	        sessionFactory.setHibernateProperties(hibernateProperties());

	        return sessionFactory;
	    }


	  private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(
	          "hibernate.hbm2ddl.auto", "update");
	        hibernateProperties.setProperty(
	          "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
	        return hibernateProperties;
	    }
	


	  @Bean
	    public HibernateTransactionManager hibernateTransactionManager() {
		  HibernateTransactionManager transactionManager
	          = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
}
