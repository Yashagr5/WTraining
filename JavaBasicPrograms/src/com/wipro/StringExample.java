package com.wipro;

import java.util.Scanner;

public class StringExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Real use cases
		//Email format
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string");
		String email = sc.next().trim().toLowerCase();
		if(email.contains("@"))
		{
			System.out.println("valid email format");
		}
		else
		{
			System.out.println("Valid email format  is required");
		}
		
		// Password
	
				System.out.println("Enter the password");
				String password = sc.next();
				if(password.equalsIgnoreCase("Admin@123"))
				{
					System.out.println("Password is correct ");
				}
				else 
				{
					System.out.println("Password is incorrect TryAgain! ");
				}
		//when you connecting with the database  (properties url =" " , username , "" , password ="" )
				
		String text = "ssflkjlk djflkj ksdfjlj";
		
		String[] afterSplitting  = text.split(" ");
		
		String value = afterSplitting[0];
		
		// To reverse
		String values = "Java Class";
		String newvalue = new StringBuilder(values).reverse().toString();
		System.out.println("The new value after reversing is :" + newvalue);

		// String Builder and String Buffer are mutable 
		
		StringBuilder sb = new StringBuilder("Niti");
	}

}

/*java.util.regex.Pattern and java.util.regex.Matcher classes are used.
A common regular expression for basic email validation is:
Java

String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
Explanation of the Regex:
^: Asserts the start of the string.
[A-Za-z0-9+_.-]+: Matches one or more occurrences of alphanumeric characters, plus, underscore, dot, or hyphen for the local part (before @).
@: Matches the literal "@" symbol.
[A-Za-z0-9.-]+: Matches one or more occurrences of alphanumeric characters, dot, or hyphen for the domain name.
\\.: Matches a literal dot, escaping it with a backslash.
[A-Za-z]{2,}: Matches the top-level domain (TLD), which must consist of at least two alphabetic characters. 
$: Asserts the end of the string.
Oops, something went wrong.

*/



//package com.wipro;
//
//public class StringExample {
//
//	public static void main(String[] args) {
//String s = "Yash";
//System.out.println(s.hashCode());
//System.out.println(System.identityHashCode(s));
//String s1= new String("Yash");
//System.out.println(s1.hashCode());
//System.out.println(System.identityHashCode(s1));
//
//System.out.println(s==s1); // different reference values
//System.out.println(s.equals(s1)); // it checks the content

		
// String concatenation
//String sValue1="Yash";
//String sValue2 = "Ya"+"sh";
//final String sValue3 = "Ya";
//final String sValue4 = "sh";
//String newValue =sValue3+sValue4; //it will be constant at compile time only  -- So compiler will recognize the final values as constant at compile time.out.println(sValue1 == newValue);
//
//String a1 = "Niti";
//String b1 = new String("Niti");
//
//String c1 = b1.intern(); // we are manually telling to the compiler to put this string into the pool 
//
//System.out.println(a1==b1); //false 
//System.out.println(a1==c1); // true


//literal 
//new keyword
//hashCode()
//identityHashCode()
//concatenation at compile time and runtime
//==  ( comparing the memory reference)
//.equals (comparing the content)
//.intern() (forcing manually to use it as a string pool version)
//	}
//
//}




//package com.wipro;
//
//public class StringExample {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		// Creating a string object using a literal
//		
//		String s = "Niti";  // String Pool in Heap 
//		System.out.println(s.hashCode());
//		// this gives the actual memory identity where it is stored
//		System.out.println(System.identityHashCode(s));
//		//  creating a string object using new keyword
//		
//		String s1 = new String("Nitin"); // Heap  
//		
//		System.out.println(s1.hashCode());  //if same content as S object has then it will give the same hash
//		System.out.println(System.identityHashCode(s1)); // different memory and different value
//		
//		
//		// Whenever the String class overrides
//		// the hashCode() method then it is computing on the basis 
//		// of the content not the memory location
//		
//		System.out.println(s==s1); // different memory references
//		System.out.println(s.equals(s1)); // it check the content
//		
//	
//		
//	}
//
//}
