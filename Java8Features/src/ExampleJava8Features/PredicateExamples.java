package ExampleJava8Features;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<String>  words = List.of("Java" , " CSharp" , "Python" , "C++" , "Encapsulation" , "Abstraction");
		
		
		// It gives a boolean valued function with one input and returns a boolean i.e. boolean test(T t);
		Predicate<String> isLong = s->s.length() > 4 ;  // Taking an input in a String and returning boolean value only
		System.out.println(isLong.test("Hello"));  // true or false
		System.out.println(isLong.test("Hello String Value")); // true or false
		System.out.println(isLong.test("He"));
		
		
		
		Predicate<String> isLong1 = s -> s.length() > 6 ;  // predicate for boolean value
		
		List<String> longWords  =words.stream()
								.filter(isLong1)
								.collect(Collectors.toList());
		
		// 
		// How the stream works :   on collections only 
		
		/* from that collection stream will take the data and perform some operations like filter , sort and then give back to the collection to print */
		
		System.out.println("The longest words which are greater than 6 letters : " + longWords);

	}

}
