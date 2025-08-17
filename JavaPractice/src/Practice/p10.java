//Java Program to Find Factorial Using Recursion
package Practice;

import java.util.*;

public class p10 {
	static int Factorial(int num) {
		if(num<=1) {
			return 1;
		}
		int ans = num*Factorial(num-1);
		return ans;
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number for finding Factorial: ");
		int num = in.nextInt();
		int ans = Factorial(num);
		System.out.println(ans);
	}

}
