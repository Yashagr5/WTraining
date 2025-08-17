package ExampleJava8Features;

import java.util.function.Consumer;

// It accept input & do something but not returning void accept(T,t)
public class ConsumerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String> greetings = name ->System.out.println("Hello"+name); // 
		greetings.accept("Yash");

	}

}
