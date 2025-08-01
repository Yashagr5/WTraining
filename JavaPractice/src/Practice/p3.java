//Java Program to Find Largest Number in an Array
package Practice;
import java.util.*;
public class p3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Size of Array: ");
		int num = in.nextInt();
		int[] arr = new int[num];
		int ans=0;
		for(int i=0; i<num; i++) {
			arr[i] = in.nextInt();
			if(ans<arr[i]) {
				ans=arr[i];
			}
		}
		System.out.println("Largest number:" + ans);
	}

}
