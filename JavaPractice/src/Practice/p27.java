//Java Program to Find the Second Largest Number in an Array
package Practice;

import java.util.Scanner;

public class p27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number of Elements");
		int num = in.nextInt();
		int[] arr = new int[num];
		for(int i=0; i<num; i++) {
			arr[i]=in.nextInt();
		}
		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		
		for(int i=0; i<num; i++) {
			if(arr[i]>largest) {
				secondLargest=largest;
				largest=arr[i];	
			}else if(arr[i] > secondLargest && arr[i] != largest) {
				secondLargest = arr[i];
			}
		}
		if(secondLargest==Integer.MIN_VALUE) {
			System.out.println("No second larggest Element found");
		}
		else {
			System.out.println("Second Largest Number is: "+secondLargest);
		}
	}

}
