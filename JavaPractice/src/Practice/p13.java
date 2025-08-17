//Java Program to Find Prime Numbers
package Practice;

import java.util.*;

public class p13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number to find it prime or not: ");
		int num = in.nextInt();
		boolean ans=true;
		for(int i=2; i<num; i++) {
			if(num%i==0) {
				ans=false;
				break;
			}
		}
		if(ans==false) {
			System.out.println("It's not a prime Number");
		}
		else {
			System.out.println("It's a prime Number");
		}
			
	}

}
