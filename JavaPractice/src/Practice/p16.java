//Java Program for Prime Numbers Within a Range
package Practice;

import java.util.*;

public class p16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		System.out.println("Enter Range: ");
		System.out.println("Enter Starting of range: ");
		int start = in.nextInt();
		System.out.println("Enter Ending of range: ");
		int end = in.nextInt();
		int[] arr = new int[end];
		for(int i=start; i<end+1; i++) {
			int a=i;
			int j=2;
			while(j<i) {
				if(a%j==0) {
					break;
				}
				j++;
			}
			if(j==i) {
				System.out.println(i);
			}
		}

	}

}
