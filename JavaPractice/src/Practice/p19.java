//Java Program to Find the Fibonacci Series
package Practice;

import java.util.*;

public class p19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		int a=0;
		int b = 1;
		System.out.print(a+" "+b);
		if(num<2) {
			System.out.println("Answer is: "+num);
		}
		for(int i=2; i<num+1; i++) {
		int temp = a+b;
		a=b;
		b=temp;
		System.out.print(" "+b+" ");
		}
		
	}

}
