package com.wipro;

public class Assignment_Classkashif {

}
//Solution FILE 
//
//
//package com.wipro;
//
//import java.util.Scanner;
//
//public class EmployeeMainProgram {
//
//	public static void main(String[] args) 
//	{
//		//Creating array for Employee
//		
//		Scanner sc= new Scanner(System.in);
//
//		
//		while(true)
//		{
//			
//			
//			System.out.println(" 1: add");
//			System.out.println("Enter 2: view");
//			System.out.println("Enter 3: update");
//			System.out.println("Enter 4: search");
//			System.out.println("Enter 5: delete");
//			System.out.println("Enter 6: exit");
//			System.out.println("Enter your choice");
//			int choice=sc.nextInt();
//			switch(choice)
//			{
//				case 1:
//					Employee.Add();
//					break;
//				case 2:
//					Employee.View();
//					break;
//				case 3:
//					Employee.Update();
//					break;
//				case 4:
//					Employee.Search();
//					break;
//				case 5:
//					Employee.Delete();
//					break;
//				case 6:
//					Employee.Exit();
//					break;
//				default:
//					System.out.println("Invalid Choice");
//			}
//		}
//	}
//
//}
//
//package com.wipro;
//import java.util.Scanner;
//import java.lang.String;
//
////Create an employee class (id,name ,position and salary) 
////then create an array of employee objects and then
////* create a menu driven program  to add , view, search by 
////name(where string contains , ignore case),
////* Update employee by ID , delete employee by ID and then exit
////* 
////* also use some of string operations like .equalsIngnoreCase , 
////.contains , .toLowerCase for search and .trim for input cleaning.
//
//public class Employee 
//{
//	int id;
//	String name;
//	String position;
//	long salary;
//	
//	static int companysize=1;
//	static Employee[] emp= new Employee[companysize];
//	static int count=0;
//	
//	//created constructor for Employee Class
//	Employee(int id,String name,String position,long salary)
//	{
//		this.id=id;
//		this.name=name;
//		this.position=position;
//		this.salary=salary;
//	}
//	
//	
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
//	}
//
//
//	//Function to display the Employee Details
//	public static void Add()
//	{
//		
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Enter your ID: ");
//		int id=sc.nextInt();
//		System.out.println("Enter your Name: ");
//		String name=sc.next();
//		System.out.println("Enter your Position: ");
//		String position=sc.next();
//		System.out.println("Enter your Salary: ");
//		long salary=sc.nextInt();
//		System.out.println("Details added");
//		
//		emp[count++] = new Employee(id,name,position,salary);
//		
//		
//	}
//	
//	public static void Update()
//	{
//		
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Enter ID to update");
//		int id=sc.nextInt();
//		for(int i=0;i<companysize;i++)
//		{
//			if (emp[i].id==id)
//			{
//				System.out.println("Enter New Name: ");
//				emp[i].name=sc.next();
//				System.out.println("Enter your Position: ");
//				emp[i].position=sc.next();
//				System.out.println("Enter your Salary: ");
//				emp[i].salary=sc.nextInt();
//				System.out.println("Details Updated");
//			}
//		}
//		
//	}
//	
//	public static void View()
//	{
//		
//		for(int i=0;i<companysize;i++)
//		{
//			System.out.println("ID : "+emp[i].id);
//			System.out.println("Name : "+emp[i].name);
//			System.out.println("Position : "+emp[i].position);
//			System.out.println("Salary : "+emp[i].salary);
//			
//		}
//	}
//	public static void Search()
//	{
//	
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Enter name to search");
//		String nameSearch=sc.next().toLowerCase();
//		for(int i=0;i<companysize;i++)
//		{
//			if(emp[i].name.contains(nameSearch))
//			{
//				emp[i].View();
//			}
//		}
//	}
//	
//	public static void Delete()
//	{
//		
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Enter ID to be deleted");
//		int givenid=sc.nextInt();
//		for(int i=0;i<companysize;i++)
//		{
//			if(emp[i].id==givenid)
//			{
//				emp[i]=null;
//			}
//			else {
//				System.out.println("ID not found");
//			}
//		}
//	}
//	public static void Exit()
//	{
//		System.exit(0);
//	}
//
//}
