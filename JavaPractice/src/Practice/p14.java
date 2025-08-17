//Java Program to Check Armstrong Number
package Practice;

import java.util.*;

public class p14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		int a=num;
		int c=num;
		int countdigits=0;
		while(a!=0) {
			countdigits++;
			a=a/10;
		}
		int ans=0;
		for(int i=0; i<countdigits; i++) {
			int b=c%10;
			ans=(int) (ans+Math.pow(b, countdigits));
			c=c/10;
		}

if(ans==num) {
System.out.println("Right ans: ");
}
else {
	System.out.println("Wrong ans: ");
}
	}
}
