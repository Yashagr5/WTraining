package ExampleJava8Features;

import java.util.function.Supplier;

//It will produce the result without input  -- T get()
public class SupplierExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Supplier<Double> randomNumbers = () -> Math.random(); // no input but returning only the Double
		System.out.println("Random Values are : " + randomNumbers.get());
	}

}
