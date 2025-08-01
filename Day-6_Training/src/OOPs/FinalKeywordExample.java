package OOPs;

public class FinalKeywordExample {

	public static void main(String[] args) {
	
 }
}











//package OOPs;
//
//final class  ConstantVariables{
//	public static final double gst = 0.18;
//	public static final double pi = 3.14;
//	public static final double bonus = 1000;
//}
//class Salary{
//	public final double salary(double basicPay) {
//		return basicPay*ConstantVariables.gst;
//	}
//}
//
//public class FinalKeywordExample {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//Salary s = new Salary();
//double sl = s.salary(40000);
//System.out.println("The Calculated salary is :"+sl);
//	}
//
//}


//package com.example;
//
////Class cannot be declared as Private or protected 
//
//final class ConstantsVariables
//{
//	public static final double gst = 0.18;
//	public static final double pi = 3.14;
//	public static final int bonus = 1000;
//	
//	public static final String companyName = "Wipro";
//}
//
//class Salary   // default is the access specifier for a class when it is not declared 
//{
//	
//public final double salary(double basicPay)
//{
//	  
//	  return basicPay*ConstantsVariables.gst;
//	  
//	  
//}
//
//}
//
//
//public class FinalKeywordExample {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		Salary s = new Salary();
//		System.out.println("Directly calling the method " + s.salary(40000));
//		double sal = s.salary(40000);
//		//System.out.println(sal+2000);
//		System.out.println("The calculated salary is :" + sal);
//
//	}
//
//}

//Use cases  :   final variables -  In malls, ECommerce for discount ,  In Banks for interest ( EMI cal) , Maths computations  , In company id cards  - common code will allocated for registration  , Bank IFSC code
//final class - grades can be defined in a final class , In Google Pay for checking the payment confirmation using UPI

//So User class is immutable
/* public final class User
{

private final int id;
private final string name;

private User(int id , String name)
{
this.id =id;
this.name = name;
}
public int getId()
{return id;}
public int getName()
{return name;}

}

/*
* public final class SecurityValidation{
* public void login(String user)
*
* System.out.println(user + " has logged in")
* }
*  //Below method cannot be overridded or extended
*  public void validateToken(String token)
*  {
*    // logic regarding token validation

*/
