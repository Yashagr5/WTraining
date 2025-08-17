package ExampleJava8Features;

import java.util.function.Predicate;

public class CalculatorOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Calculation add  = (a, b) ->a+b;
Calculation sub = (a, b) ->a-b;

System.out.println("Addition of two numbers: "+ add.Calculator(45,67));
System.out.println("Substraction of two numbers: "+ sub.Calculator(45,67));

Predicate<String> isLong = s->s.length()>5;
	}

}


//CalculatorOperations c  = new CalculatorOperations();
//System.out.println(c.Calculator(34, 56));
//	
//}
//	@Override
//public double (double a,double b) {
//	// TODO Auto-generated method stub
//	
//	return a+b; // statement
//	
//}

