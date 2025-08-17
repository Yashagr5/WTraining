//Java Program to Count the Number of Digits in a Number
package Practice;

import java.util.Scanner;

public class p15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		int countdigits=0;
		while(num!=0) {
			countdigits++;
			num=num/10;
		}
		System.out.println("Here is number of Digits: "+countdigits);
	}

}
