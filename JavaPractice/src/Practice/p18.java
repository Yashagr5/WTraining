//Java Program to Check if a Number is Prime
package Practice;

import java.util.*;

public class p18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while(true) {
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		int i=2;
		while(i<num) {
			if(num%i==0) {
				break;
			}
			i++;
		}
		if(i==num) {
			System.out.println("Number is Prime");
		}
		else {
			System.out.println("Number is not Prime");
		}
		}
		}
}
