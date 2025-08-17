//Java Program to Swap Two Numbers Without Using a Temp Variable
package Practice;

import java.util.Scanner;

public class p25 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter First Number: ");
		int num1 = in.nextInt();
		System.out.println("Enter Second  Number: ");
		int num2 = in.nextInt();
		System.out.println("Before Swapping: num1: "+ num1+" num2: "+ num2);
		num1=num1+num2;
		num2=num1-num2;
		num1 = num1-num2;
		
		System.out.println("After Swapping: num1: "+ num1+" num2: "+ num2);
	}

}
