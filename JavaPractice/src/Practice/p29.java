//Java Program to Check if a Given Number is Perfect Square
package Practice;

import java.util.Scanner;
//import java.lang.Math;

public class p29 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = in.nextInt();

        int n1 = (int) Math.sqrt(num);
        double n2 = Math.sqrt(num);

        if (n1 == n2) {
            System.out.println("Number is a Perfect Square");
        } else {
            System.out.println("Number is Not a Perfect Square");
        }
    }
}