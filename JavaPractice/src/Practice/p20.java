//Java Program to Check if a Number is Positive or Negative
package Practice;

import java.util.Scanner;

public class p20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		if(num<0){
			System.out.println("Number is Negative");
		}
		else {
			System.out.println("Number is Positive");
		}
	}

}
