//Java Program to Find the First Non-repeated Character in a String
package Practice;

import java.util.Scanner;

public class p30 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter String: ");
		String st = in.nextLine();
		int l = st.length();
		char ans='a';
		for(int i=0; i<l; i++) {
			int count=0;
			for(int j=i+1; j<l; j++) {
				if(st.charAt(i)==st.charAt(j)) {
					count++;
				}
			}
			if(count==0) {
				ans=(char)st.charAt(i);	
			}
			break;
		}
		System.out.println("Answer is: "+ans);

	}

}
