//Java Program to Find GCD of Two Numbers

package Practice;

import java.util.Scanner;

public class p28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter First Nnumber: ");
		int num1 = in.nextInt();
		System.out.println("Enter Second Number: ");
		int num2 = in.nextInt();
		
		while(num2>0) {
			if(num1%num2==0) {
				num1=num2;
				break;
			}
			int temp = num2;
			num2=num1%num2; 
			num1 = temp;
		}
		System.out.println("GCD IS: "+num1);
	}

}
