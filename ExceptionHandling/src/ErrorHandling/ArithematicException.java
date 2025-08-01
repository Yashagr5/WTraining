package ErrorHandling;

public class ArithematicException {
static int Divide(int num1, int num2) {
	return num1/num2;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	int num = Divide(40,2);
	System.out.println("Result after division: "+ num);
}
catch(Exception e) {
	System.out.println("Error handled");
}
finally {
	System.out.println("Division Done");
}
	}

}
