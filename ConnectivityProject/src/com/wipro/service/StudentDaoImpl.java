package com.wipro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.wipro.dao.StudentDao;
import com.wipro.util.DBConnUtil;

public class StudentDaoImpl implements StudentDao{
	Connection conn = DBConnUtil.getConn();
	Scanner in = new Scanner(System.in);
	
	@Override
	public void addStudent() {
		
		// query 
		String query = "insert into student(name, age) values(?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			System.out.println("Enter Name");
			String Name = in.nextLine();
			System.out.println("Enter Age");
			int Age = in.nextInt();
			ps.setString(1, Name);
			ps.setInt(2, Age);
			
			int i = ps.executeUpdate();
			
			if (i>0)
			{
				System.out.println("Record is added");
			}
			else 
			{
				System.out.println("Record is not added");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewStudent() {
		
		String query2 = "Select * from Student";
		
		try {
			Statement s = conn.createStatement();
			// it is returning in tabular format so we are using result set interface
			ResultSet rs  = s.executeQuery(query2);
			
			// we are placing the pointer on each row one by one
			while(rs.next())
			{
				int id  = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				System.out.println("Id :" + id + " " + " Name : " + name + " Age :" + age );
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateStudent() {
		String query = "UPDATE student SET name=?, age=? where id=?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			System.out.println("Enter Updated Name");
			String UpdatedName = in.nextLine();
			System.out.println("Enter Updated Age");
			int UpdatedAge = in.nextInt();
			System.out.println("Enter Updated ID");
			int UpdatedId = in.nextInt();
			ps.setString(1, UpdatedName);
			ps.setInt(2, UpdatedAge);
			ps.setInt(3, UpdatedId);
			int i=ps.executeUpdate();
			
			if(i>0) {
				System.out.println("Record Updated.");
			}
			else {
				System.out.println("No record found");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteStudentById() {
		// TODO Auto-generated method stub
		String query = "Delete from student where id=?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			System.out.println("Enter ID to Delete");
			int DeleteId = in.nextInt();
			
			ps.setInt(1, DeleteId);
			
			int i = ps.executeUpdate();
			
			if(i>0) {
				System.out	.println("Record Deleetd");
			}
			else {
				System.out.println("Record not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewStudentById() {
		// TODO Auto-generated method stub
		String query = "Select * from student where id=?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			System.out.println("Enter ID");
			int viewId = in.nextInt();
			
			ps.setInt(1, viewId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("ID: "+rs.getInt("id")+" Name: "+rs.getString("name")+" Age: "+rs.getInt("age"));
			}
			else {
				System.out.println("No Record Found By ID");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
