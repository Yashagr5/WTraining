//Java Program to Find the Largest of Three Numbers
package Practice;

import java.util.Scanner;

public class p17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter First Number: ");
		int num1 = in.nextInt();
		System.out.println("Enter Second Number: ");
		int num2 = in.nextInt();
		System.out.println("Enter Third Number: ");
		int num3 = in.nextInt();
		
		if((num1>num2)&&(num1>num3)) {
			System.out.println("Maximum Number is: "+num1);
		}
		else if((num2>num1)&&(num2>num3)) {
			System.out.println("Maximum Number is: "+num2);
		}
		else {
				System.out.println("Maximum Number is: "+num3);
		}
	}

}
