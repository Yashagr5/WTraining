////Java Program to Check Palindrome Number
//package Practice;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class p24 {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
//		System.out.println("Enter Number: ");
//		int n = in.nextInt();
//		int temp= n;
//		int count=0;
//		while(n>0) {
//			temp=temp/10;
//			count++;
//		}
//		int i=0;
//		boolean flag = true;
//		int[] arr = new int[count];
//		while(i<count) {
//			if(arr[i]!=arr[count-1]) {
//				flag=false;
//				break;
//			}
//			i++;
//			count--;
//		}
//		if(flag==true) {
//			System.out.println("It is valid Palindrome").
//		}
//		else {
//			System.out.println("It is Not Palindrome").	
//		}
//	}
//
//}
