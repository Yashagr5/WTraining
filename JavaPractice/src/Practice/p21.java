//Java Program to Find GCD of Two Numbers
package Practice;

import java.util.Scanner;

public class p21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter first Number: ");
		int num1 = in.nextInt();
		System.out.println("Enter Second Number: ");
		int num2 = in.nextInt();
		
		while(num2!=0) {
			int temp = num1;
			num1 = num2;
			num2 = temp%num2;
		}
		System.out.println("Answerr is: "+num1);
	}

}
