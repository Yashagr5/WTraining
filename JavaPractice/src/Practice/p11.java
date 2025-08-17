//Java Program to Find Sum of Natural Numbers
package Practice;

import java.util.*;

public class p11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number greater than zero: ");
		int n = in.nextInt();
		int ans=0;
		for(int i=1; i<n+1; i++) {
			ans=ans+i;
		}
		System.out.println("Here is the ans: "+ans);
	}

}
