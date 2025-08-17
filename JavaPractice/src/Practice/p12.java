//Java Program to Reverse a Number
package Practice;

import java.util.*;

public class p12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number to reverse: ");
		int num = in.nextInt();
		int ans=0;
		while(num>0) {
			ans=(ans*10)+num%10;
			num=num/10;
		}
		System.out.println("Reverse Number is: "+ans);
	}

}
