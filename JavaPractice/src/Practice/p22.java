//Java Program to Find Strong Number
package Practice;

import java.util.Scanner;

public class p22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int num = in.nextInt();
		int temp = num;
		int sum=0;
		while(temp>0) {
			int a = temp%10;
			temp=temp/10;
			int b=1;
			for(int i=1; i<a+1; i++) {
				b=b*i;
			}
				sum=sum+b;
		}
		 
		if(sum==num) {
			System.out.println("Is Strong Number: "+num);
		}
		else {
			System.out.println("Is Not Strong Number: "+num);
		}
	}

}
