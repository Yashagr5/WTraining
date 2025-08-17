package ExampleJava8Features;

import java.util.function.Function;

//@FunctionalInterface : In this we only use one abstract method otherwise it throws an error
//interface Calculation{
//	void add();
//	void substract();
//}

//Here it takes one input(T) and return a result(R)  -- R apply(T t)
public class FunctionalInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function<String, Integer> toLength  = s->s.length();
		
		System.out.println("Length of value is : " + toLength.apply("Yash")); // here it takes string and return integer

	}

}
