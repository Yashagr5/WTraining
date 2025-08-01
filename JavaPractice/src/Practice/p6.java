//Java Program to Sort the Array Elements in Descending Order
package Practice;
import java.util.*;

public class p6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of values: ");
			int num = in.nextInt();
			int[] arr = new int[num];
			for(int i=0; i<num; i++) {
				arr[i]=in.nextInt();
			}
			Arrays.sort(arr);
			int i=0;
			while(i<num) {
				int temp = arr[i];
				arr[i] = arr[num-1];
				arr[num-1]=temp;
				i++;
				num--;
			}
			System.out.println("Here are Array Elements in Descending Order: "+ Arrays.toString(arr));
			
			
	}

}
