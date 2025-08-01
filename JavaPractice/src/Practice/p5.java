//Java Program to Calculate Average Using Arrays
package Practice;
import java.util.*;
public class p5 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner in = new Scanner(System.in);
System.out.println("Enter number of values: ");
	int num = in.nextInt();
	int[] arr = new int[num];
	int sum=0;
	for(int i=0; i<num; i++) {
		arr[i]=in.nextInt();
		sum=sum+arr[i];
	}
	int ans = sum/num;
	System.out.println("Average of given Numbers are: "+ans);
	
	}
}
