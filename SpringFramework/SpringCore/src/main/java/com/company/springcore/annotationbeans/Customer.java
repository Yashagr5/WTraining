package com.company.springcore.annotationbeans;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	
	private int id;
	private String name;
	private double salary;
	
	
	
//	public Customer(int id, String name, double salary) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.salary = salary;
//	}



	public void welcomeCustomer()
	{
		
		System.out.println("Annotation Based Config File");
		System.out.println("ID is: "+id+" Name is: "+name+" Salary is: "+salary);
	}
	
	public Customer() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter ID: ");
			this.id = sc.nextInt();
			System.out.println("Enter Name: ");
			this.name = sc.nextLine();
			System.out.println("Enter Salary: ");
			this.salary = sc.nextDouble();
		}
	}
}
