package com.wipro.studentmanagement;

import java.util.Scanner;

import com.wipro.service.StudentDaoImpl;

public class StudentApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentDaoImpl stud = new StudentDaoImpl();
		Scanner in = new Scanner(System.in);
		while(true) {
		System.out.println("Enter Choice : 1. Add   2. View  3. Update  4. Delete  5.View:");
		int choice = in.nextInt();
		switch(choice) {
		case 1 -> stud.addStudent();	
		case 2 -> stud.viewStudent();
		case 3 -> stud.updateStudent();
		case 4 -> stud.deleteStudentById();
		case 5 -> stud.viewStudentById();
		case 6 -> System.exit(0);
		default -> System.out.println("Enter a Valid Number");
		}
		}
	}

}