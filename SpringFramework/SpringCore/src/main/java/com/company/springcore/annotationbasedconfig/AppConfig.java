package com.company.springcore.annotationbasedconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@ComponentScan(basePackages = "com.company.springcore.xmlbasedconfig,com.company.springcore.annotationbeans")
public class AppConfig {
//	@Bean
//	public Customer customer() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter ID: ");
//		int id = sc.nextInt();
//		System.out.println("Enter Name: ");
//		String name = sc.nextLine();
//		System.out.println("Enter Salary: ");
//		double salary = sc.nextDouble();
//		return new Customer(id, name, salary);
//	}
}
