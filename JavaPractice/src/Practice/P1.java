//Java Program to Reverse an Array Without Using Another Array
package Practice;

import java.util.*;

public class P1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter length of array: ");
		int num = in.nextInt();
		int[] arr = new int[num];
		for(int i=0; i<num; i++) {
			arr[i]=in.nextInt();
		}
		
	int s=0;
	while(s<num) {
			int temp = arr[num-1];
			arr[num-1] = arr[s];
			arr[s] = temp;
			
		s++;
		num--;
	}
	System.out.println("Reversed array: "+Arrays.toString(arr));

	}

}
